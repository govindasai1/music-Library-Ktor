package com.example.services

import com.example.dao.AdminDao
import com.example.models.AdminLogin
import com.example.models.Message
import com.example.models.Song
import com.example.models.SongTitle
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdminServices : KoinComponent {
    private val admin by inject<AdminDao>()

    suspend fun adminRegistationService() {
        admin.adminReg()
    }

    suspend fun adminLoginService(details: AdminLogin): Message {
        return admin.adminLogin(details)
    }

    suspend fun addSongsService(song: Song): Message {
        return admin.addSongs(song)
    }

    suspend fun removeSongsService(songTitle: SongTitle): Message {
        return admin.removeSongs(songTitle.songTitle)
    }
}