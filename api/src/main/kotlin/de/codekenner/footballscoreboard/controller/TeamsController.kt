package de.codekenner.footballscoreboard.controller

import de.codekenner.footballscoreboard.ApiGatewayRequest
import de.codekenner.footballscoreboard.ApiGatewayResponse
import de.codekenner.footballscoreboard.domain.Team
import de.codekenner.footballscoreboard.routing.Controller
import org.apache.logging.log4j.LogManager

class TeamsController : Controller {
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

    companion object {
        private val LOG = LogManager.getLogger(TeamsController::class.java)
    }
}