package com.thevarungupta.model

data class User(
    val id: Int,
    val fullName: String,
    val avatar: String,
    val email: String,
    var authToken: String? = null,
    var createdAt: String
)
