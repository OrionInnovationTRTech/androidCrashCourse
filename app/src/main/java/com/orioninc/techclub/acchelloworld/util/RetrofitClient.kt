package com.orioninc.techclub.acchelloworld.util

import com.orioninc.techclub.acchelloworld.data.api.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val SERVICE_BASE_URL = "https://reqres.in/"

    private val restLogger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(restLogger)
        .build()

    private val retrofitClient = Retrofit.Builder()
        .baseUrl(SERVICE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val userService = retrofitClient.create(UserService::class.java)
}