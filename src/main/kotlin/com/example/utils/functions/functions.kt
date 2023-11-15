package com.example.utils.functions

import com.example.database.DatabaseFactory
import com.example.entitys.PlayListEntity
import com.example.entitys.SongEntity
import com.example.entitys.UserEntity
import com.example.tables.SongsTable
import com.example.tables.UserTable
import com.example.utils.entityMapping.entityToPlayList
import com.example.utils.entityMapping.entityToSongId
import com.example.utils.entityMapping.entityToUserId

suspend fun gettingUserIdByName(userName:String):Int?{
    return DatabaseFactory.dbQuery { UserEntity.find { UserTable.userName.eq(userName) }.map { entityToUserId(it) } }.singleOrNull()
}

suspend fun gettingSongIdByTitle(songTitle:String):Int?{
    return DatabaseFactory.dbQuery { SongEntity.find { SongsTable.songTitle.eq(songTitle) }.map { entityToSongId(it) } }.singleOrNull()
}
suspend fun checkingPlayList(playList:String):Boolean{
    val result =  DatabaseFactory.dbQuery { PlayListEntity.all().map { entityToPlayList(it) } }.toList()
    return result.contains(playList)
}
