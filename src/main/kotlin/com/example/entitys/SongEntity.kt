package com.example.entitys

import com.example.tables.SongsTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SongEntity(id:EntityID<Int>):IntEntity(id){
    companion object:IntEntityClass<SongEntity>(SongsTable)
    var songTitle by SongsTable.songTitle
    var songArtist by SongsTable.songArtist
    var songDuration by SongsTable.songDuration
}