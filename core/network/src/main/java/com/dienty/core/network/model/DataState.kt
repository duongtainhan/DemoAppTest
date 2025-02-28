package com.dienty.core.network.model

sealed interface BaseResult<out T> {
    data class Success<out T>(val data: T) : BaseResult<T>
    object SuccessNoData : BaseResult<Nothing>
    data class Failure<out T>(val exception: ResponseException) : BaseResult<T>
}

suspend fun <T> BaseResult<T>.doWhenSuccessCoroutine(block: suspend (T) -> Unit): BaseResult<T> {
    if (this is BaseResult.Success) {
        block.invoke(this.data)
    }
    return this
}

suspend fun <T> BaseResult<T>.doWhenSuccessNoDataCoroutine(
    block: suspend () -> Unit
): BaseResult<T> {
    if (this is BaseResult.SuccessNoData) {
        block.invoke()
    }
    return this
}

suspend fun <T> BaseResult<T>.doWhenFailureCoroutine(
    block: suspend (ResponseException) -> Unit
): BaseResult<T> {
    if (this is BaseResult.Failure) {
        block.invoke(this.exception)
    }
    return this
}
