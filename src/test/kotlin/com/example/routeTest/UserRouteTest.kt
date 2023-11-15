package com.example.routeTest

import com.example.testParemeters.*
import com.example.utils.endpoints.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRouteTest {
    @Test
    fun testRegistation() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(USER_BASE + REGISTER) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(userRegDetails)
        }
        assertEquals(HttpStatusCode.Created, responce.status)
    }

    @Test
    fun testLoginTest() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(USER_BASE + LOGIN) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(userLogin)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testPlatSongTest() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(USER_BASE + PLAY_SONG) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(songTitle)
        }
        println("------>  ${responce.bodyAsText()}")
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testFilterArtist() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(USER_BASE + FILTER) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(artist)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testCreatePlayList() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(USER_BASE + PLAYLIST) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(newPlaylist)
        }
        assertEquals(HttpStatusCode.Created, responce.status)
    }

    @Test
    fun testAddSongPlayList() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(USER_BASE + PLAYLIST_SONG) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(playListSong)
        }
        assertEquals(HttpStatusCode.Accepted, responce.status)
    }

    @Test
    fun testRemoveSongFromPlayList() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.delete(USER_BASE + REMOVE_SONG) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(playListSong)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testRemovePlayList() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.delete(USER_BASE + REMOVE_PLAYLIST) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(playListDetails)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testRemoveAccount() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.delete(USER_BASE + REMOVE_ACCOUNT) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(userLogin)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }
}