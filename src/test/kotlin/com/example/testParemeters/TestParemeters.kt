package com.example.testParemeters

import com.example.models.*
import kotlinx.serialization.Serializable

@Serializable
val adminLogin = AdminLogin("Govindasai", "Govind@123")

@Serializable
val song = Song("SUMMER HIGH", "AP DHILLON", "02:57")

@Serializable
val newSong = Song("JALSA","DSP","03:40")

@Serializable
val songTitle = SongTitle("SUMMER HIGH")

@Serializable
val userRegDetails = UserReg("govindasai101","govindasai@gmail.com","Govindsai@123")

@Serializable
val userLogin = UserLogin("govindasai101","Govindsai@123")

@Serializable
val artist = Artist("AP DHILLON")

@Serializable
val newPlaylist = NewPlayList("PUNJABI","govindasai101","SUMMER HIGH")

@Serializable
val playListSong = NewPlayList("PUNJABI","govindasai101","JALSA")

@Serializable
val playListDetails = RemovePlayListDetails("PUNJABI","govindasai101")