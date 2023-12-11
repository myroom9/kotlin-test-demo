package com.example.kotlintestdemo.application.port.out

import com.example.kotlintestdemo.adapter.out.persistence.MemberJpaEntity

interface SaveMemberPort {

    fun createMember(
        name: String,
        email: String,
        password: String
    ): MemberJpaEntity

}