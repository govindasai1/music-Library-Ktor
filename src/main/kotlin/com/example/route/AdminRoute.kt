package com.example.route

import com.example.models.AdminLogin
import com.example.models.Song
import com.example.models.SongTitle
import com.example.services.AdminServices
import com.example.utils.endpoints.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.adminRouting() {
    val adminService by inject<AdminServices>()
    route(ADMIN_BASE) {
        post(REGISTER) {
            call.respond(HttpStatusCode.Created,adminService.adminRegistationService())
        }
        post(LOGIN) {
            val request = call.receive<AdminLogin>()
            call.respond(adminService.adminLoginService(request))
        }
        post(ADMIN_ADDSONG) {
            val request = call.receive<Song>()
            call.respond(adminService.addSongsService(request))
        }
        delete(ADMIN_REMOVESONG) {
            val request = call.receive<SongTitle>()
            call.respond(adminService.removeSongsService(request))
        }
    }
}