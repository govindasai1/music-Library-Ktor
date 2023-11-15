package com.example

import com.example.database.DatabaseFactory
import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    logging()
    koin()
    statusPages()
    configureSerialization()
    configureRouting()
}
