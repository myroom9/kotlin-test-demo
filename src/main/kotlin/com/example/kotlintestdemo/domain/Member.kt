package com.example.kotlintestdemo.domain

class Member(
    id: Long,
    name: String,
    email: String,
    password: String
) {

    var id: Long = id
        private set

    var name: String = name
        private set

    var email: String = email
        private set

    var password: String = password
        private set

}