package com.example.kotlintestdemo.adapter.`in`

import com.example.kotlintestdemo.application.port.`in`.SaveMemberUseCase
import com.example.kotlintestdemo.domain.Member
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.apache.coyote.BadRequestException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@WebMvcTest(controllers = [SaveMemberController::class])
@AutoConfigureMockMvc
class SaveMemberControllerTest {
    // https://zzang9ha.tistory.com/382
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var saveMemberUseCase: SaveMemberUseCase

    private val objectMapper = ObjectMapper()

    @Test
    @DisplayName("saveMemberControllerSuccessTest")
    fun saveMemberControllerSuccessTest() {

        every {
            saveMemberUseCase.saveMember(any(), any<String>(), any())
        } returns Member(1L, "name", "email", "password")

        val request = SaveMemberRequest("name", "email", "password")
        val jsonRequest = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/members")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value("OK"))
    }

    @Test
    @DisplayName("saveMemberControllerFailTest")
    fun saveMemberControllerFailTest() {

        every {
            saveMemberUseCase.saveMember(any(), any<String>(), any())
        }.throws(BadRequestException("BAD REQUEST!!"))

        val request = SaveMemberRequest("name", "email", "password")
        val jsonRequest = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/members")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect { result ->
                assertTrue(result.resolvedException is BadRequestException)
                assertEquals("BAD REQUEST!!", result.resolvedException?.message)
            }
    }
}