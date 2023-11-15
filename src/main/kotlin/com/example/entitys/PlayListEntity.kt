package com.example.entitys

import com.example.tables.PlayListTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PlayListEntity(id:EntityID<Int>):IntEntity(id) {
    companion object:IntEntityClass<PlayListEntity>(PlayListTable)
    var playListName by PlayListTable.playListName
    var userId by  PlayListTable.userId
    var songId by  PlayListTable.songId
}