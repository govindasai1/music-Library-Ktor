package com.example.route

import com.example.models.*
import com.example.services.UserServices
import com.example.utils.endpoints.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRouting() {
    val userService by inject<UserServices>()
    route(USER_BASE) {
        post(REGISTER) {
            val request = call.receive<UserReg>()
            call.respond(HttpStatusCode.Created, userService.userRegistationService(request))
        }
        post(LOGIN) {
            val request = call.receive<UserLogin>()
            call.respond(userService.userLoginService(request))
        }
        post(PLAY_SONG) {
            val request = call.receive<SongTitle>()
            call.respond(userService.playSongService(request.songTitle))
        }
        post(FILTER) {
            val request = call.receive<Artist>()
            call.respond(userService.filterByArtistService(request.artist))
        }
        post(PLAYLIST) {
            val request = call.receive<NewPlayList>()
            call.respond(HttpStatusCode.Created, userService.createPlayListService(request))
        }
        post(PLAYLIST_SONG) {
            val request = call.receive<NewPlayList>()
            call.respond(HttpStatusCode.Accepted, userService.addSongToPlaylistService(request))
        }
        delete(REMOVE_SONG) {
            val request = call.receive<NewPlayList>()
            call.respond(userService.removeSongFromPlayListService(request))
        }
        delete(REMOVE_PLAYLIST) {
            val request = call.receive<RemovePlayListDetails>()
            call.respond(userService.deletePlayListService(request))
        }
        delete(REMOVE_ACCOUNT){
            val request = call.receive<UserLogin>()
            call.respond(userService.removeAccount(request))
        }

    }
}