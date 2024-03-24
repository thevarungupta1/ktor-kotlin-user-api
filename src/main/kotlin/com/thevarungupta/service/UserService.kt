package com.thevarungupta.service

import com.thevarungupta.model.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByEmail(email: String): User?
}