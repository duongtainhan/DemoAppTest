package com.dienty.common.remote.service

import com.dienty.common.model.response.DetailResponse
import com.dienty.common.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users?per_page=20&since=100")
    suspend fun getUsers(@Query("per_page") perPage: Int, @Query("since") since: Int): List<UserResponse?>?

    @GET("users/{username}")
    suspend fun getDetail(@Path("username") username: String): Response<DetailResponse?>?
}