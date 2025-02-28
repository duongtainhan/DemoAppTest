package com.dienty.core.network

import com.dienty.core.network.model.BaseResult
import com.dienty.core.network.model.ResponseException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend inline fun <T> safeApiCall(crossinline block: suspend () -> Response<T>): BaseResult<T> {
    return try {
        block.invoke().run {
            if (isSuccessful) {
                body()?.run { BaseResult.Success(this) } ?: BaseResult.SuccessNoData
            } else {
                BaseResult.Failure(ResponseException(code().toString(), message()))
            }
        }
    } catch (exception: Throwable) {
        when (exception) {
            is IOException -> BaseResult.Failure(ResponseException.from(exception))
            is HttpException -> BaseResult.Failure(
                ResponseException(exception.code().toString(), exception.message())
            )
            else -> BaseResult.Failure(ResponseException.from(exception))
        }
    }
}