package com.example.routeTest


import com.example.testParemeters.adminLogin
import com.example.testParemeters.newSong
import com.example.testParemeters.song
import com.example.testParemeters.songTitle
import com.example.utils.endpoints.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class AdminRouteTest {


    @Test
    fun testRegistation() = testApplication {
        val responce = client.post(ADMIN_BASE + REGISTER) {}
        assertEquals(HttpStatusCode.Created, responce.status)
    }

    @Test
    fun testLogin()= testApplication{
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(ADMIN_BASE + LOGIN){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(adminLogin)
        }
        assertEquals(HttpStatusCode.OK,responce.status)
    }

    @Test
    fun testAddSong()= testApplication{
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(ADMIN_BASE + ADMIN_ADDSONG){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(newSong)
        }
        assertEquals(HttpStatusCode.OK,responce.status)
    }
    @Test
    fun testAddNewSong()= testApplication{
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(ADMIN_BASE + ADMIN_ADDSONG){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(song)
        }
        assertEquals(HttpStatusCode.OK,responce.status)
    }

    @Test
    fun testRemoveSong()= testApplication{
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.delete(ADMIN_BASE + ADMIN_REMOVESONG){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(songTitle)
        }
        assertEquals(HttpStatusCode.OK,responce.status)
    }
}