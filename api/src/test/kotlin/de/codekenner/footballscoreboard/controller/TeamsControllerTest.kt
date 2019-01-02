package de.codekenner.footballscoreboard.controller

import com.nhaarman.mockitokotlin2.mock
import de.codekenner.footballscoreboard.ApiGatewayRequest
import de.codekenner.footballscoreboard.ApiGatewayResponse
import de.codekenner.footballscoreboard.domain.Team
import de.codekenner.footballscoreboard.repository.Repository
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class TeamsControllerTest : StringSpec({
    val repo: Repository<Team> = mock()
    val controller = TeamsController(repo)

    "should fail with empty body " {
        val request = ApiGatewayRequest(mapOf("body" to """{}"""), mock())
        controller.create(request) shouldBe ApiGatewayResponse.UNPROCESSABLE_ENTITY
    }

    "should create team if data complete" {
        val request = ApiGatewayRequest(mapOf("body" to """{"id": "foo", "name": "bar"}"""), mock())
        controller.create(request) shouldBe ApiGatewayResponse.CREATED
    }
})