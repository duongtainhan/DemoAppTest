package com.dienty.core.network.logging

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import java.io.IOException
import java.net.SocketTimeoutException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * Custom interceptor responsible for printing curl logs
 */
class CurlLoggingInterceptor : Interceptor {

    private val logger = HttpLoggingInterceptor.Logger.DEFAULT
    private val curlGenerator = CurlCommandGenerator(Configuration())

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request: Request = chain.request()
            val url = curlGenerator.generate(request)

            //request of api
            val requestBodyBuffer = Buffer()
            request.body?.writeTo(requestBodyBuffer)
            val requestBody = requestBodyBuffer.clone().readUtf8()

            //startNs: time at start to send request api
            val startNs = System.nanoTime()

            try {
                Log.i("REQUEST_API","╭---> REQUEST ${request.url}")
                Log.i("REQUEST_API","| $url")
                Log.i("REQUEST_API","| RequestBody: $requestBody")
                Log.i("REQUEST_API","╰--->")
            } catch (e: Exception) {
                Log.i("REQUEST_API","Can't log request: $url")
            }

            //response: response api
            val response: Response
            //try request api
            try {
                response = chain.proceed(request)
            } catch (e: SocketTimeoutException) {
                //log curl when api timeout
                logger.log("╭--- TIMEOUT ${request.url}")
                logger.log("| $url")
                logger.log("| Body: $requestBody")
                logger.log("╰---> HTTP FAILED: $e")
                throw e
            } catch (e: Exception) {
                logger.log("╭--- ERROR ${request.url}")
                logger.log("| $url")
                logger.log("| Body: $requestBody")
                logger.log("╰---> ERROR MESSAGE: $e")
                throw e
            }
            //tookMs: time to receive response from api since api request
            val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

            val responseBody = response.body
            val source = responseBody?.source()
            source?.request(Long.MAX_VALUE) // Buffer the entire body.
            val contentType = responseBody?.contentType()

            val buffer = source?.buffer
            var charset = Charset.forName("UTF-8")
            if (contentType != null) charset = contentType.charset(charset)

            val responseBodyString = if (responseBody?.contentLength() != 0L) {
                buffer?.clone()?.readString(charset) ?: ""
            } else ""

            //log curl when api success
            logger.log(
                "╭--- ${response.code}${
                    if (response.message.isEmpty()) "" else ' ' + response.message
                } ${response.request.url}"
            )
            logger.log("| $url")
            if (requestBody.isNotBlank()) {
                if (request.method != "PUT") logger.log("| Body: $requestBody")
            }
            if (responseBodyString.isNotBlank()) {
                logger.log("| Response: $responseBodyString")
            }
            logger.log("╰---> (${tookMs}ms) END HTTP (${buffer?.size}-byte body)")
            return response
        } catch (e: Exception) {
            return chain.proceed(chain.request())
        }
    }
}