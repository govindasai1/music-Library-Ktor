package com.example.plugins

import com.example.exceptions.CommonException
import com.example.exceptions.DatabaseConflictException
import com.example.exceptions.DuplicateSongException
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.statusPages(){
    install(StatusPages) {
        exception<DuplicateSongException> { call, cause ->
            call.respondText(text = cause.errorMessage , status = cause.code)
        }
        exception<DatabaseConflictException>{ call, cause ->
            call.respondText(text = cause.errorMessage , status = cause.code)
        }
        exception<CommonException>{ call, cause ->
            call.respondText(text = cause.errorMessage , status = cause.code)
        }
    }
}