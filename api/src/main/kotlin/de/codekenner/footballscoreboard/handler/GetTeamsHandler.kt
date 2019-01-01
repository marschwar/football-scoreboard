package de.codekenner.footballscoreboard.handler

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import de.codekenner.footballscoreboard.domain.Team
import org.apache.logging.log4j.LogManager

class GetTeamsHandler : RequestHandler<Map<String, Any>, ApiGatewayResponse> {
    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("received: " + input.keys.toString())
        return ApiGatewayResponse.build {
            objectBody = listOf(Team("foo", "bar"))
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(GetTeamsHandler::class.java)
    }
}
