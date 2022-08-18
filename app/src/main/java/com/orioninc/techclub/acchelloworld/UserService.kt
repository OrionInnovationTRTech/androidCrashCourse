package com.orioninc.techclub.acchelloworld

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/api/login")
    suspend fun login(@Body body: LoginBody): Response<LoginResponse>
}