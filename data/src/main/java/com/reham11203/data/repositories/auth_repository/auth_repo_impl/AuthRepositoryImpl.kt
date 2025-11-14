package com.reham11203.data.repositories.auth_repository.auth_repo_impl

import com.reham11203.data.repositories.auth_repository.datasources.remote.auth_remote_datasource.AuthRemoteDataSource
import com.reham11203.data.di.ConnectivityChecker
import com.reham11203.data.utils.PrefsHelper
import com.reham11203.domain.model.request.RegistrationRequest
import com.reham11203.domain.repositories.AuthRepository
import com.reham11203.domain.utils.ApiResult
import com.reham11203.domain.utils.AppErrors
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val connectivityChecker: ConnectivityChecker,
    private val prefsHelper: PrefsHelper
    ): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): ApiResult<Unit> {
        return if (connectivityChecker.isOnline()){
            val response = authRemoteDataSource.login(email, password)
            when(response){
                is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(response.error)
                is ApiResult.SuccessApiResult -> {
                    prefsHelper.saveToken(response.data?.token)
                    prefsHelper.saveUser(response.data?.user)
                    ApiResult.SuccessApiResult()
                }
            }
        }else
        {
            ApiResult.ErrorApiResult(AppErrors.NetworkError())
        }
    }

    override suspend fun register(registrationRequest: RegistrationRequest): ApiResult<Unit> {

        return if (connectivityChecker.isOnline()){
            val response = authRemoteDataSource.register(registrationRequest)
            when(response){
                is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(response.error)
                is ApiResult.SuccessApiResult -> {
                    prefsHelper.saveToken(response.data?.token)
                    prefsHelper.saveUser(response.data?.user)
                    ApiResult.SuccessApiResult()
                }
            }
        }else
        {
            ApiResult.ErrorApiResult(AppErrors.NetworkError())
        }

    }
}