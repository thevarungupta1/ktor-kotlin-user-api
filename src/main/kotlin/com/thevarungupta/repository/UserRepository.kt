package com.thevarungupta.repository

import com.thevarungupta.service.CreateUserParams
import com.thevarungupta.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(email: String, password: String): BaseResponse<Any>
}