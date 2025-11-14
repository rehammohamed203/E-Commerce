package com.reham11203.domain.repositories

import com.reham11203.domain.model.request.RegistrationRequest
import com.reham11203.domain.utils.ApiResult

interface AuthRepository {

    suspend fun login(email:String, password:String) : ApiResult<Unit>

    suspend fun register(registrationRequest: RegistrationRequest) : ApiResult<Unit>
}