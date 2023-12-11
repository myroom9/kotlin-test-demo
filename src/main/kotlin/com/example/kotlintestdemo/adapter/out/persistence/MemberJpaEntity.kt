package com.example.kotlintestdemo.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberJpaEntity (
    name: String,
    email: String,
    password: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var name = name
        private set

    var email = email
        private set

    var password = password
        private set

}