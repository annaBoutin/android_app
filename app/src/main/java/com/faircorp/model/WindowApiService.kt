package com.faircorp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WindowApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("windows/{id}")
    fun findById(@Path("id") id: Long): Call<WindowDto>

    @GET("windows/room/{id}")
    fun findByRoomId(@Path("id") id: Long): Call<List<WindowDto>>
}