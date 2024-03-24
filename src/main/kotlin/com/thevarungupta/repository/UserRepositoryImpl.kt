package com.thevarungupta.repository

import com.thevarungupta.service.CreateUserParams
import com.thevarungupta.service.UserService
import com.thevarungupta.utils.BaseResponse

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = "email already registered")
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                BaseResponse.SuccessResponse(data = user)
            } else {
                BaseResponse.ErrorResponse()
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }
}