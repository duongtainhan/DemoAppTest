package com.dienty.core.network.logging

import okhttp3.*
import okio.Buffer
import okio.buffer
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class CurlCommandGenerator(private val configuration: Configuration) {

    fun generate(request: Request): String = configuration.components
        .flatMap { generateCommandComponent(it, request) }
        .joinToString(separator = configuration.delimiter)

    private fun generateCommandComponent(
        commandComponent: CommandComponent,
        request: Request
    ): List<String> = when (commandComponent) {
        CommandComponent.Curl -> listOf("curl")
        CommandComponent.Url -> generateUrl(request.url)
        CommandComponent.Flags -> generateFlags()
        CommandComponent.Body -> generateBody(request.body)
        CommandComponent.Method -> generateMethod(request.method)
        CommandComponent.Header -> generateHeaders(request.headers, request.body)
    }

    private fun generateBody(body: RequestBody?): List<String> =
        if (body != null) listOf(getBodyAsString(body)) else emptyList()

    private fun getBodyAsString(body: RequestBody) = try {
        val sink = Buffer()
        val mediaType = body.contentType()
        val charset: Charset = getCharset(mediaType)
        if (configuration.limit > 0) {
            val buffer = LimitedSink(sink, configuration.limit).buffer()
            body.writeTo(buffer)
            buffer.flush()
        } else {
            body.writeTo(sink)
        }
        FORMAT_BODY.format(sink.readString(charset))
    } catch (e: IOException) {
        "Error while reading body: $e"
    }

    private fun generateHeaders(headers: Headers, body: RequestBody?): List<String> {
        val curlHeaders = mutableListOf<CurlHeader>()
        for (i in 0 until headers.size) {
            curlHeaders.add(CurlHeader(headers.name(i), headers.value(i)))
        }
        return curlHeaders.mapNotNull { header -> modifyHeader(header) }
            .applyContentTypeHeader(body)
            .map { header -> FORMAT_HEADER.format(header.name, header.value) }
    }


    private fun modifyHeader(header: CurlHeader): CurlHeader? {
        val modifier = configuration.headerModifiers.find { it.matches(header) }
        return if (modifier != null) modifier.modify(header) else header
    }

    private fun generateMethod(method: String): List<String> =
        listOf(FORMAT_METHOD.format(method.uppercase(Locale.getDefault())))

    private fun generateFlags(): List<String> = configuration.flags.list()

    private fun generateUrl(url: HttpUrl): List<String> = listOf(FORMAT_URL.format(url.toString()))

    private fun getCharset(mediaType: MediaType?): Charset {
        val default = Charset.defaultCharset()
        return mediaType?.charset(default) ?: default
    }


    private fun List<CurlHeader>.applyContentTypeHeader(body: RequestBody?): List<CurlHeader> {
        val contentTypeHeader = find { it.name.equals(CONTENT_TYPE, ignoreCase = false) }
        val contentType = body?.contentType()?.toString()

        return if (contentTypeHeader == null && contentType != null) {
            this + listOf(CurlHeader(CONTENT_TYPE, contentType))
        } else {
            this
        }
    }

    private companion object {
        const val FORMAT_HEADER = "-H \"%1\$s:%2\$s\""
        const val FORMAT_METHOD = "-X %1\$s"
        const val FORMAT_BODY = "-d '%1\$s'"
        const val FORMAT_URL = "\"%1\$s\""
        const val CONTENT_TYPE = "Content-Type"
    }
}