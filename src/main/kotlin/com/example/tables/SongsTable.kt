package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object SongsTable:IntIdTable("Songs") {
    val songTitle = varchar("SongTitle",50).uniqueIndex()
    val songArtist = varchar("SongArtist",50)
    val songDuration = varchar("songDuration",50)
}