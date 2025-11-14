package com.reham11203.data.utils

import com.reham11203.domain.utils.ApiResult
import com.reham11203.domain.utils.AppErrors

fun <T>handleError(throwable: Throwable) : ApiResult.ErrorApiResult<T>{
    return ApiResult.ErrorApiResult(AppErrors.ServerError(throwable.message ?: ""))
}