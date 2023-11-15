package com.example.databaseTest

import org.jetbrains.exposed.sql.Database

object DatabaseFactoryTest {
    fun init():Database{
        val url = "jdbc:postgresql://localhost:8080/TestMusicLibrary"
        val driver = "org.postgresql.Driver"
        val userName = "postgres"
        val password = "root"
        return Database.connect(url, driver,userName,password)
    }
}