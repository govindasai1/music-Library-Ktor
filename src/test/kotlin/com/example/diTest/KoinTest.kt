package com.example.diTest

import com.example.di.koinModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun moduleTest() {
        koinModule.verify()
    }
}