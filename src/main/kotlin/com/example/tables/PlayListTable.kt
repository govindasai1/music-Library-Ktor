package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object PlayListTable:IntIdTable("PlayList") {
    val playListName = varchar("PlayListName",50)
    val userId = integer("userId").references(UserTable.id)
    val songId =integer("SongId").references(SongsTable.id)
}