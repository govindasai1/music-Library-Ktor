package com.example.dao

import com.example.models.*

interface UserDao {
    suspend fun userRegistation(userReg: UserReg):Message
    suspend fun userLogin(details: UserLogin):Message
    suspend fun playSong(songTitle:String):Message
    suspend fun filterByArtist(artist:String):List<Song>
    suspend fun createPlayList(playListDetails:NewPlayList):Message
    suspend fun addSongToPlaylist(playListName: String,userName: String,songTitle: String):Message
    suspend fun removeSongFromPlayList(playListNam: String,userName: String,songTitle: String):Message
    suspend fun deletePlayList(playListName: String,userName: String):Message
    suspend fun removeAccount(details: UserLogin):Message
}