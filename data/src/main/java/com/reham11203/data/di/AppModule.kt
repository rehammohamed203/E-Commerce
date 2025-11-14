package com.reham11203.data.di

import com.reham11203.data.repositories.auth_repository.auth_repo_impl.AuthRepositoryImpl
import com.reham11203.data.repositories.auth_repository.datasources.remote.auth_remote_datasource.AuthRemoteDataSource
import com.reham11203.data.repositories.auth_repository.datasources.remote.auth_remote_datasource.AuthRemoteDataSourceImpl
import com.reham11203.domain.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindsAuthRepo(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

    @Binds
    fun bindsAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl) : AuthRemoteDataSource

}