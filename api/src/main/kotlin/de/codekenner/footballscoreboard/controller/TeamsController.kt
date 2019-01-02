package de.codekenner.footballscoreboard.controller

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.codekenner.footballscoreboard.ApiGatewayRequest
import de.codekenner.footballscoreboard.ApiGatewayResponse
import de.codekenner.footballscoreboard.ApiGatewayResponse.Companion.UNPROCESSABLE_ENTITY
import de.codekenner.footballscoreboard.domain.Team
import de.codekenner.footballscoreboard.repository.Repository
import org.apache.logging.log4j.LogManager


class TeamsController(private val repository: Repository<Team>) : Controller {
    override fun index(request: ApiGatewayRequest): ApiGatewayResponse {
        return ApiGatewayResponse.build {
            objectBody = listOf(Team("foo", "bar"))
        }
    }

    override fun show(request: ApiGatewayRequest): ApiGatewayResponse {
        val id = request.pathVariables["id"] ?: "unknown"

        return ApiGatewayResponse.build {
            objectBody = Team(id, "bar")
        }
    }

    override fun create(request: ApiGatewayRequest): ApiGatewayResponse {
        return request.body?.let {
            try {
                val team = jacksonObjectMapper().readValue(it, Team::class.java)
                LOG.info("Saving " + team)
                repository.save(team)
                ApiGatewayResponse.CREATED
            } catch (e: JsonMappingException) {
                null
            }
        } ?: UNPROCESSABLE_ENTITY
    }

    companion object {
        private val LOG = LogManager.getLogger(TeamsController::javaClass)
    }
}