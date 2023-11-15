package com.example.testRepository

import com.example.databaseTest.DatabaseFactoryTest
import com.example.repository.AdminDaoImp
import com.example.repository.UserDaoImp
import com.example.tables.AdminTable
import com.example.tables.PlayListTable
import com.example.tables.SongsTable
import com.example.tables.UserTable
import com.example.testParemeters.*
import com.example.utils.responces.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("UNUSED_VARIABLE")
class AdminDaoImpTest {
    private lateinit var database: Database
    private val adminObj = AdminDaoImp()
    private val userObj = UserDaoImp()

    @Before
    fun start(){
            database =DatabaseFactoryTest.init()
            TransactionManager.manager.defaultIsolationLevel =Connection.TRANSACTION_REPEATABLE_READ
            transaction {
                SchemaUtils.create(AdminTable,PlayListTable,SongsTable,UserTable)
            }
    }

    @After
    fun end(){
            transaction { SchemaUtils.drop(AdminTable,PlayListTable,SongsTable,UserTable) }
    }

    @Test
    fun adminRegTest() = runBlocking {
        val result = adminObj.adminReg()
        assertTrue(true)
    }

    @Test
    fun adminLoginTest() = runBlocking {
        val result = adminObj.adminLogin(adminLogin)
        assertEquals(result.message, SUCCESS_LOGIN.message)
    }

    @Test
    fun addSongTest()= runBlocking{
        val result = adminObj.addSongs(song)
        assertEquals(result.message,SUCCESS_SONGADD.message)
    }

    @Test
    fun userRegistationTest() = runBlocking {
        val result = userObj.userRegistation(userRegDetails)
        assertEquals(result.message, SUCCESS_REGISTATION.message)
    }
    @Test
    fun userLoginTest() = runBlocking {
        val result = userObj.userLogin(userLogin)
        assertEquals(result.message,SUCCESS_LOGIN.message)
    }

    @Test
    fun playSongTest() = runBlocking {
        val result = userObj.playSong(song.title)
        assertTrue(result.message.isNotEmpty())
    }

    @Test
    fun filterByArtistTest() = runBlocking {
        val result = userObj.filterByArtist(artist.artist)
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun createPlayListTest() = runBlocking {
        val result = userObj.createPlayList(newPlaylist)
        assertEquals(result.message, SUCCESS_PLAYLIST.message)
    }

    @Test
    fun addSongToPlaylistTest() = runBlocking {
        val result = userObj.addSongToPlaylist(playListSong.playListName, playListSong.userName, playListSong.songTitle)
        assertEquals(result.message, SUCCESS_SONGADD.message)
    }

    @Test
    fun removeSongFromPlayListTest() = runBlocking {
        val result = userObj.removeSongFromPlayList(playListSong.playListName, playListSong.userName, playListSong.songTitle)
        assertEquals(result.message, SUCCESS_REMOVAL.message)
    }

}