package com.example.kotlintestdemo.application.port.`in`

import com.example.kotlintestdemo.domain.Member

interface SaveMemberUseCase {

    fun saveMember(name: String, email: String, password: String): Member

}