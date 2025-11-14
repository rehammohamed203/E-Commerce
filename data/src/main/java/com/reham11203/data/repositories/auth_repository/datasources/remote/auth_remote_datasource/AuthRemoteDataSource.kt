package com.reham11203.data.repositories.auth_repository.datasources.remote.auth_remote_datasource

import com.reham11203.data.model.response.TokenResponse
import com.reham11203.domain.model.request.RegistrationRequest
import com.reham11203.domain.utils.ApiResult

interface AuthRemoteDataSource {

    suspend fun login(email:String, password:String) : ApiResult<TokenResponse>

    suspend fun register(registrationRequest: RegistrationRequest) : ApiResult<TokenResponse>
}