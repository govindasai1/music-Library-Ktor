package com.example.di

import com.example.dao.AdminDao
import com.example.dao.UserDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.repository.AdminDaoImp
import org.koin.core.module.dsl.bind
import com.example.repository.UserDaoImp
import com.example.services.AdminServices
import com.example.services.UserServices

val koinModule = module {
    singleOf(::AdminDaoImp) {bind<AdminDao>()}
    singleOf(::UserDaoImp){bind<UserDao>()}
    singleOf(::AdminServices)
    singleOf(::UserServices)
}