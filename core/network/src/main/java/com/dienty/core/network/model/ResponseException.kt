package com.dienty.core.network.model

data class ResponseException(
    val errorCode: String?,
    val errorMessage: String? = null
) : Exception(errorMessage) {

    companion object {

        fun from(error: Throwable): ResponseException {
            return ResponseException("UnknownErrorCode", error.message)
        }

        fun from(errorCode: String, errorMessage: String?): ResponseException {
            return ResponseException(errorCode, errorMessage)
        }
    }

    override fun toString(): String {
        return "ResponseException(errorCode=$errorCode, errorMessage=$errorMessage)"
    }

}