package com.example.repository

import com.example.dao.AdminDao
import com.example.database.DatabaseFactory
import com.example.entitys.AdminEntity
import com.example.entitys.PlayListEntity
import com.example.entitys.SongEntity
import com.example.exceptions.DatabaseConflictException
import com.example.exceptions.DuplicateSongException
import com.example.models.AdminLogin
import com.example.models.Message
import com.example.models.Song
import com.example.tables.AdminTable
import com.example.tables.PlayListTable
import com.example.tables.SongsTable
import com.example.utils.entityMapping.entityToAdmin
import com.example.utils.entityMapping.entityToId
import com.example.utils.entityMapping.entityToSongId
import com.example.utils.responces.FAILURE_LOGIN
import com.example.utils.responces.SUCCESS_LOGIN
import com.example.utils.responces.SUCCESS_SONGADD
import io.ktor.http.*
import org.jetbrains.exposed.sql.and

class AdminDaoImp:AdminDao {
    override suspend fun adminReg() {
        try {
            val adminDetails = AdminLogin("Govindasai", "Govind@123")
            DatabaseFactory.dbQuery {
                AdminEntity.new {
                    adminName = adminDetails.adminName
                    password = adminDetails.password
                }
            }
        }catch (e:Exception){
            throw DatabaseConflictException("CONFLICT OCCURRED AT DATABASE",HttpStatusCode.OK)
        }
    }

    override suspend fun adminLogin(details: AdminLogin):Message {
        try {
            val query = DatabaseFactory.dbQuery {
                AdminEntity.find {
                    AdminTable.adminName.eq(details.adminName) and AdminTable.password.eq(details.password)
                }.map { entityToAdmin(it) }
            }.singleOrNull()
            return if (query != null) SUCCESS_LOGIN
            else FAILURE_LOGIN
        }catch (e:Exception){
            throw DatabaseConflictException("CONFLICT OCCURRED AT DATABASE",HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun addSongs(song: Song):Message {
        try {
            DatabaseFactory.dbQuery {
                SongEntity.new {
                    songTitle = song.title
                    songArtist = song.artist
                    songDuration = song.duration
                }
            }
            return SUCCESS_SONGADD
        }catch (e:Exception){

            throw DuplicateSongException("DUPLICATE SONG CANT BE ADDED", HttpStatusCode.NotAcceptable)
        }
    }

    override suspend fun removeSongs(songTitle: String):Message {
        val c =DatabaseFactory.dbQuery { SongEntity.find { SongsTable.songTitle.eq(songTitle) }.map { entityToSongId(it) } }.singleOrNull()
        return if (c!=null){
            val q = DatabaseFactory.dbQuery { PlayListEntity.find { PlayListTable.songId.eq(c) }.map { entityToId(it) } }.toList()
            if (q.isNotEmpty()){
                for (i in q.indices){
                    DatabaseFactory.dbQuery { PlayListEntity[i].delete() }
                }
                DatabaseFactory.dbQuery { SongEntity[c].delete() }
                Message("$songTitle DELETED SUCCESSFULLY")
            }else{
                DatabaseFactory.dbQuery { SongEntity[c].delete() }
                Message("$songTitle DELETED SUCCESSFULLY")
            }
        } else Message("$songTitle NOT FOUND")
    }
}