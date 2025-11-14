package com.reham11203.data.repositories.auth_repository.datasources.remote.auth_remote_datasource

import com.reham11203.data.api.webservices.WebServices
import com.reham11203.data.model.response.TokenResponse
import com.reham11203.data.utils.handleError
import com.reham11203.domain.model.request.RegistrationRequest
import com.reham11203.domain.utils.ApiResult
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(private val services: WebServices) :
    AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): ApiResult<TokenResponse> {
        try {
            val tokenResponse = services.login(email, password)
            return ApiResult.SuccessApiResult(tokenResponse)
        }catch (throwable : Throwable){
            return handleError(throwable)
        }

    }

    override suspend fun register(registrationRequest: RegistrationRequest): ApiResult<TokenResponse> {
        try {
            val tokenResponse = services.register(registrationRequest)
            return ApiResult.SuccessApiResult(tokenResponse)
        }catch (throwable : Throwable){
            return handleError(throwable)
        }
    }
}