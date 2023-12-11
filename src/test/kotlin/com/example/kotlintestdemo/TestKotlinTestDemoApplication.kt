package com.example.kotlintestdemo

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestKotlinTestDemoApplication

fun main(args: Array<String>) {
    fromApplication<KotlinTestDemoApplication>().with(TestKotlinTestDemoApplication::class).run(*args)
}
