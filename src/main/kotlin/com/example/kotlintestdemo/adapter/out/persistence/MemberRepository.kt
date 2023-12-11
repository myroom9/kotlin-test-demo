package com.example.kotlintestdemo.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberJpaEntity, Long> {
}