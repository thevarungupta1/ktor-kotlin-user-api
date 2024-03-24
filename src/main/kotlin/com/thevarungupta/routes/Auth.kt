package com.thevarungupta.routes

import com.thevarungupta.repository.UserRepository
import com.thevarungupta.service.CreateUserParams
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.authRoutes(respository: UserRepository){
    routing {
        route("/auth"){
            post("/register"){
                val params = call.receive<CreateUserParams>()
                val result = respository.registerUser(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}