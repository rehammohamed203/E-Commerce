package com.reham11203.domain.usecases

import com.reham11203.domain.repositories.AuthRepository
import com.reham11203.domain.utils.ApiResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun execute(email: String, password: String) : ApiResult<Unit>{
        return authRepository.login(email, password)
    }
}