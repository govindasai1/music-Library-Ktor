package com.example.entitys

import com.example.tables.AdminTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AdminEntity(id:EntityID<Int>) : IntEntity(id){
    companion object:IntEntityClass<AdminEntity>(AdminTable)
    var adminName by AdminTable.adminName
    var password by AdminTable.password
}