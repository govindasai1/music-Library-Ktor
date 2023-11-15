package com.example.dao

import com.example.models.AdminLogin
import com.example.models.Message
import com.example.models.Song

interface AdminDao {
    suspend fun adminReg()
    suspend fun adminLogin(details:AdminLogin):Message
    suspend fun addSongs(song: Song):Message
    suspend fun removeSongs(songTitle: String):Message
}