package com.example.services

import com.example.dao.UserDao
import com.example.models.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserServices : KoinComponent {
    private val user by inject<UserDao>()

    suspend fun userRegistationService(userReg: UserReg): Message {
        return user.userRegistation(userReg)
    }

    suspend fun userLoginService(details: UserLogin): Message {
        return user.userLogin(details)
    }

    suspend fun playSongService(songTitle: String): Message {
        return user.playSong(songTitle)
    }

    suspend fun filterByArtistService(artist: String): List<Song> {
        return user.filterByArtist(artist)
    }

    suspend fun createPlayListService(playListDetails: NewPlayList): Message {
        return user.createPlayList(playListDetails)
    }

    suspend fun addSongToPlaylistService(playListDetails: NewPlayList): Message {
        return user.addSongToPlaylist(playListDetails.playListName, playListDetails.userName, playListDetails.songTitle)
    }

    suspend fun removeSongFromPlayListService(playListDetails: NewPlayList): Message {
        return user.removeSongFromPlayList(
            playListDetails.playListName,
            playListDetails.userName,
            playListDetails.songTitle
        )
    }

    suspend fun deletePlayListService(playListDetails: RemovePlayListDetails): Message {
        return user.deletePlayList(playListDetails.playListName, playListDetails.userName)
    }

    suspend fun removeAccount(details: UserLogin): Message {
        return user.removeAccount(details)
    }
}