package com.example.exceptions

import io.ktor.http.*

class DuplicateSongException(val errorMessage:String,val code:HttpStatusCode):RuntimeException()
class DatabaseConflictException(val errorMessage: String,val code: HttpStatusCode):RuntimeException()
class CommonException(val errorMessage: String,val code: HttpStatusCode):RuntimeException()