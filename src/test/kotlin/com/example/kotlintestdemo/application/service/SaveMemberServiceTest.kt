package com.example.kotlintestdemo.application.service

import com.example.kotlintestdemo.adapter.out.persistence.MemberJpaEntity
import com.example.kotlintestdemo.application.port.out.SaveMemberPort
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk

val saveMemberPort: SaveMemberPort = mockk()

@InjectMockKs
val saveMemberService: SaveMemberService = SaveMemberService(saveMemberPort)

// https://kotest.io/docs/framework/testing-styles.html
// @ExtendWith(MockKExtension::class)
class SaveMemberServiceTest(
    /*@MockK
    private val memberPersistenceAdapter: MemberPersistenceAdapter,
    @InjectMockKs
    private val saveMemberService: SaveMemberService*/
) : BehaviorSpec({

    /*@AnnotationSpec.BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }*/

    given("a new member") {

        every {
            saveMemberPort.createMember("test", "test", "test")
        } returns MemberJpaEntity( "test", "test", "test")

        `when`("the member is saved") {

            val saveMember = saveMemberService.saveMember("test", "test", "test")

            then("the member is saved") {
                saveMember.name shouldBe "test"
                saveMember.email shouldBe "test"
                saveMember.password shouldBe "test"
            }
        }
    }
})