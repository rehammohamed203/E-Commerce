package com.reham11203.e_commerce.utils

import com.reham11203.domain.utils.AppErrors

sealed class Resource<T> {

    class LoadingState<T> : Resource<T>()

    class IdleState<T> : Resource<T>()

    class SuccessState<T>(var data: T? = null) : Resource<T>()

    class ErrorState<T>(val error: AppErrors) : Resource<T>()

}