package com.example.kotlintestdemo.adapter.out.persistence

import com.example.kotlintestdemo.application.port.out.SaveMemberPort
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository
) : SaveMemberPort {

    /**
     * Create a new member.
     * @param name The name of the member.
     * @param email The email of the member.
     * @param password The password of the member.
     * @return The created member.
     */
    override fun createMember(name: String, email: String, password: String): MemberJpaEntity {
        MemberJpaEntity(name, email, password).let {
            return memberRepository.save(it)
        }
    }

}