package com.dienty.common.repository.detail

import androidx.annotation.VisibleForTesting
import com.dienty.common.model.entity.DetailEntity
import com.dienty.common.model.response.toDetailEntity
import com.dienty.common.remote.service.GithubService
import com.dienty.core.network.model.BaseResult
import com.dienty.core.network.model.ResponseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailRepository @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val githubService: GithubService,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val dispatcher: CoroutineDispatcher
) {
    suspend fun getDetail(username: String): BaseResult<DetailEntity> = withContext(dispatcher) {
        try {
            githubService.getDetail(username)?.run {
                if (isSuccessful) {
                    body()?.run { BaseResult.Success(this.toDetailEntity()) } ?: BaseResult.SuccessNoData
                } else {
                    BaseResult.Failure(ResponseException(code().toString(), message()))
                }
            } ?: BaseResult.SuccessNoData
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
}