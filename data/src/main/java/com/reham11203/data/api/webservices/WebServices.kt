package com.reham11203.data.api.webservices

import com.reham11203.domain.model.request.RegistrationRequest
import com.reham11203.data.model.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WebServices {

    @FormUrlEncoded
    @POST("/api/v1/auth/signin")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): TokenResponse

    @POST("/api/v1/auth/signup")
    suspend fun register(
        @Body
        registrationRequest: RegistrationRequest
    ): TokenResponse
}