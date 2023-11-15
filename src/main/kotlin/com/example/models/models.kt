package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Song(val title: String, val artist: String, val duration: String)

@Serializable
data class UserReg(val userName: String, val email: String, val password: String)

@Serializable
data class UserLogin(val userName: String, val password: String)

@Serializable
data class NewPlayList(val playListName: String, val userName: String, val songTitle: String)

@Serializable
data class RemovePlayListDetails(val playListName: String, val userName: String)

@Serializable
data class AdminLogin(val adminName: String, val password: String)

@Serializable
data class Message(val message: String)

@Serializable
data class Session(val userName: String)

@Serializable
data class SongTitle(val songTitle: String)

@Serializable
data class Artist(val artist: String)

@Serializable
data class Playlist(val playListId:Int,val userId:Int,val songId:Int)