package com.example.plugins

import com.example.models.Session
import com.example.models.UserReg
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.configureSerialization() {
    val passwordValidator =Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$")
    install(RequestValidation){
        validate<UserReg>{
            if (it.email.contains("@gmail.com") && it.password.matches(passwordValidator)) ValidationResult.Valid
            else ValidationResult.Invalid("EMAIL ID OR PASSWORD DID NOT REACH STANDARDS")
        }
    }
    install(Sessions){
        cookie<Session>("Session")
    }
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/json/kotlinx-serialization") {
                call.respond(mapOf("hello" to "world"))
            }
    }
}
