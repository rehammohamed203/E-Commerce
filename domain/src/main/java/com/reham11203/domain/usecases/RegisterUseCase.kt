package com.reham11203.domain.usecases

import com.reham11203.domain.model.request.RegistrationRequest
import com.reham11203.domain.repositories.AuthRepository
import com.reham11203.domain.utils.ApiResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor (private val authRepository: AuthRepository){

    suspend fun execute(registrationRequest: RegistrationRequest) : ApiResult<Unit>{
        return authRepository.register(registrationRequest)
    }
}