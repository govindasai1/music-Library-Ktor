package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable:IntIdTable("UserTable") {
    val userName = varchar("UserName",50).uniqueIndex()
    val email = varchar("Email",50).uniqueIndex()
    val password = varchar("Password",50)
}