package com.orioninc.techclub.acchelloworld.data.api

import com.orioninc.techclub.acchelloworld.data.entity.LoginBody
import com.orioninc.techclub.acchelloworld.data.entity.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/api/login")
    suspend fun login(@Body body: LoginBody): Response<LoginResponse>
}