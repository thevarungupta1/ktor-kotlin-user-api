package com.thevarungupta

import com.thevarungupta.db.DatabaseFactory
import com.thevarungupta.plugins.*
import com.thevarungupta.repository.UserRepository
import com.thevarungupta.repository.UserRepositoryImpl
import com.thevarungupta.routes.authRoutes
import com.thevarungupta.service.UserService
import com.thevarungupta.service.UserServiceImpl
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 9000, host = "127.0.0.1"){
        DatabaseFactory.init()
        install(ContentNegotiation){
            jackson()
        }

        val service: UserService = UserServiceImpl()
        val respository: UserRepository = UserRepositoryImpl(service)

        authRoutes(respository)

    }
        .start(wait = true)
}

