package com.thevarungupta.utils

import io.ktor.http.*
import java.lang.Exception

sealed class BaseResponse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK
) {

    data class SuccessResponse<T>(
        val data: T? = null,
        val message: String? = null
    ): BaseResponse<T>()



    data class ErrorResponse<T>(
        val exception: T? = null,
        val message: String? = null
    ): BaseResponse<T>()

}