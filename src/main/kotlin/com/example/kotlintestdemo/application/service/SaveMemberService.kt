package com.example.kotlintestdemo.application.service

import com.example.kotlintestdemo.application.port.`in`.SaveMemberUseCase
import com.example.kotlintestdemo.application.port.out.SaveMemberPort
import com.example.kotlintestdemo.domain.Member
import org.springframework.stereotype.Service

@Service
class SaveMemberService (
    private val saveMemberPort: SaveMemberPort
) : SaveMemberUseCase {

    /**
     * Save a new member.
     * @param name The name of the member.
     * @param email The email of the member.
     * @param password The password of the member.
     * @return The created member.
     */
    override fun saveMember(name: String, email: String, password: String): Member {
        saveMemberPort.createMember(name, email, password).let {
            return Member(it.id, it.name, it.email, it.password)
        }
    }

}