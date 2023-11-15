package com.example.repository

import com.example.dao.UserDao
import com.example.database.DatabaseFactory
import com.example.entitys.PlayListEntity
import com.example.entitys.SongEntity
import com.example.entitys.UserEntity
import com.example.models.*
import com.example.tables.PlayListTable
import com.example.tables.SongsTable
import com.example.tables.UserTable
import com.example.utils.entityMapping.*
import com.example.utils.functions.checkingPlayList
import com.example.utils.functions.gettingSongIdByTitle
import com.example.utils.functions.gettingUserIdByName
import com.example.utils.responces.*
import org.jetbrains.exposed.sql.and

class UserDaoImp:UserDao {
    override suspend fun userRegistation(userReg:UserReg): Message {
        DatabaseFactory.dbQuery { UserEntity.new {
            userName = userReg.userName
            email = userReg.email
            password = userReg.password
        } }
        return SUCCESS_REGISTATION
    }

    override suspend fun userLogin(details: UserLogin):Message {
        val c =DatabaseFactory.dbQuery { UserEntity.find {
            UserTable.userName.eq(details.userName) and UserTable.password.eq(details.password)
        }.map { entityToUserId(it) } }.singleOrNull()
        return if (c!=null)  SUCCESS_LOGIN
        else FAILURE_LOGIN
    }

    override suspend fun playSong(songTitle: String):Message {
        val query = DatabaseFactory.dbQuery { SongEntity.find { SongsTable.songTitle.eq(songTitle) }.map { entityToSong(it) }}.singleOrNull()
        return if (query!=null) Message("Now playing: [${query.title}] by [${query.artist}]")
        else Message("$songTitle NOT FOUND IN LIBRARY")
    }

    override suspend fun filterByArtist(artist: String) :List<Song>{
        return DatabaseFactory.dbQuery { SongEntity.find { SongsTable.songArtist.eq(artist) }.map { entityToSong(it) } }
            .toList()
    }

    override suspend fun createPlayList(playListDetails: NewPlayList):Message {
        val songid = gettingSongIdByTitle(playListDetails.songTitle)
        val userid = gettingUserIdByName(playListDetails.userName)
        return if (userid!=null && songid!=null) {
            DatabaseFactory.dbQuery {
                PlayListEntity.new {
                    playListName = playListDetails.playListName
                    userId = userid
                    songId = songid
                }
            }
             SUCCESS_PLAYLIST
        }
        else FAILURE_CREDENTIALS
    }

    override suspend fun addSongToPlaylist(playListName: String, userName: String, songTitle: String): Message {
        val playList = checkingPlayList(playListName)
        val songid = gettingSongIdByTitle(songTitle)
        val userid = gettingUserIdByName(userName)
        return if (playList && songid!=null && userid!=null){
            DatabaseFactory.dbQuery {
                PlayListEntity.new {
                    this.playListName = playListName
                    userId = userid
                    songId = songid
                }
            }
            SUCCESS_SONGADD
        } else FAILURE_CREDENTIALS
    }

    override suspend fun removeSongFromPlayList(playListNam: String, userName: String, songTitle: String): Message {
        val playList = checkingPlayList(playListNam)
        val songid = gettingSongIdByTitle(songTitle)
        val userid = gettingUserIdByName(userName)
        return if (playList && songid != null && userid != null) {
            val play = DatabaseFactory.dbQuery { PlayListEntity.find { PlayListTable.playListName.eq(playListNam) and PlayListTable.songId.eq(songid)  and PlayListTable.userId.eq(userid)}.map { entityToPlay(it
            ) }}.singleOrNull()
            if (play!=null){
                DatabaseFactory.dbQuery { PlayListEntity[play.playListId].delete() }
                SUCCESS_REMOVAL
            } else FAILURE_SONG
        } else FAILURE_CREDENTIALS
    }

    override suspend fun deletePlayList(playListName: String, userName: String): Message {
        val playList = checkingPlayList(playListName)
        val userid = gettingUserIdByName(userName)
        println("             $playList                  $userid")
        return if (playList && userid!=null){
            val c =DatabaseFactory.dbQuery { PlayListEntity.find { PlayListTable.playListName.eq(playListName)  and PlayListTable.userId.eq(userid)}.map { entityToId(it) } }.toList()
            for (i in c.indices){
                DatabaseFactory.dbQuery {  PlayListEntity[c[i]].delete()}
            }
            SUCCESS_DELETION
        } else FAILURE_CREDENTIALS
    }

    override suspend fun removeAccount(details: UserLogin): Message {
        val c =DatabaseFactory.dbQuery { UserEntity.find {
            UserTable.userName.eq(details.userName) and UserTable.password.eq(details.password)
        }.map { entityToUserId(it) } }.singleOrNull()
        return if (c!=null){
           val listOfIds =  DatabaseFactory.dbQuery { PlayListEntity.find { PlayListTable.userId.eq(c) }.map { entityToId(it) } }.toList()
            for (i in listOfIds.indices){
                DatabaseFactory.dbQuery { PlayListEntity[i].delete() }
            }
            DatabaseFactory.dbQuery { UserEntity[c].delete() }
             Message("ACCOUNT REMOVED")
        }
        else Message("ACCOUNT NOT FOUND")
    }
}
