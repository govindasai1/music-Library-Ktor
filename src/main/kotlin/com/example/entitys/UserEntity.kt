package com.example.entitys

import com.example.tables.UserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id:EntityID<Int>):IntEntity(id){
    companion object:IntEntityClass<UserEntity>(UserTable)
    var userName by UserTable.userName
    var email by UserTable.email
    var password by UserTable.password
}