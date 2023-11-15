package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object AdminTable:IntIdTable("AdminTable") {
    val adminName = varchar("AdminName",50).uniqueIndex()
    val password = varchar("Password",50)
}