package com.faircorp.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    val windowsApiService : WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-application-boutin.cleverapps.io/api/")
            .build()
            .create(WindowApiService::class.java)
    }
    val roomsApiServices: RoomApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-application-boutin.cleverapps.io/api/")
            .build()
            .create(RoomApiService::class.java)
    }
}