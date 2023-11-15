package com.example.plugins

import com.example.route.adminRouting
import com.example.route.userRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        adminRouting()
        userRouting()
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
