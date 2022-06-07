package com.example.retrofitdemo.api

import com.example.retrofit.model.user
import com.example.userauthentication.data.cache.data.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*


interface SimpleApi {

    @GET("users")
    suspend fun getUsers():Response<List<user>>

    @POST("users")
    suspend fun pushPost(
        @Body post: user
    ):Response<user>

    @GET("users")
    suspend fun getUsersUsingFlow(): List<user>

}