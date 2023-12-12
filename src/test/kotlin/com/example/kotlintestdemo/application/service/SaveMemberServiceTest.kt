package com.example.kotlintestdemo.application.service

import com.example.kotlintestdemo.adapter.out.persistence.MemberJpaEntity
import com.example.kotlintestdemo.application.port.out.SaveMemberPort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.testcontainers.perSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import org.testcontainers.containers.GenericContainer

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

    val redisContainer = GenericContainer<Nothing>("redis:5.0.3-alpine")

    afterSpec {
        redisContainer.stop()
    }

    beforeSpec {
        redisContainer.portBindings.add("16379:6379")
        redisContainer.start()
        listener(redisContainer.perSpec())
    }

    given("새로운 회원 생성") {
        `when`("저장 요청") {

            every {
                saveMemberPort.createMember("test", "test", "test")
            } returns MemberJpaEntity( "test", "test", "test")

            val saveMember = saveMemberService.saveMember("test", "test", "test")

            then("저장이 완료됐을 때") {
                saveMember.name shouldBe "test"
                saveMember.email shouldBe "test"
                saveMember.password shouldBe "test"
            }
        }

        `when`("회원 저장 요청") {

            every {
                saveMemberPort.createMember("test", "test", "test")
            } throws IllegalArgumentException()

            then("저장을 실패했을 때") {
                shouldThrow<IllegalArgumentException> {
                    saveMemberService.saveMember("test", "test", "test")
                }
            }
        }
    }
})