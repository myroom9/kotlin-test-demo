package com.example.kotlintestdemo.adapter.`in`

import com.example.kotlintestdemo.application.port.`in`.SaveMemberUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
class SaveMemberController(
    private val saveMemberUseCase: SaveMemberUseCase
) {

    @PostMapping
    fun saveMember(@RequestBody saveMemberRequest: SaveMemberRequest): ResponseEntity<String> {

        saveMemberUseCase.saveMember(saveMemberRequest.name, saveMemberRequest.email, saveMemberRequest.password);

        return ResponseEntity.ok("OK")
    }

}