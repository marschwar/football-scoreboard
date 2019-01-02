package de.codekenner.footballscoreboard.routing

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import de.codekenner.footballscoreboard.ApiGatewayRequest
import de.codekenner.footballscoreboard.ApiGatewayResponse
import de.codekenner.footballscoreboard.HttpMethod
import de.codekenner.footballscoreboard.controller.TeamsController
import de.codekenner.footballscoreboard.repository.TeamsRepository
import org.apache.logging.log4j.LogManager

class RequestDispatcher(val routes: Map<String, Resource> = defaultRoutes()) : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        val request = ApiGatewayRequest(input, context)
        LOG.info("processing incoming request $request")
        return dispatch(request)
    }

    private fun dispatch(request: ApiGatewayRequest): ApiGatewayResponse {
        val resource = PATH_REGEX.matchEntire(request.path)?.groupValues?.get(1)?.let { routes.get(it) }

        if (resource == null) return ApiGatewayResponse.NOT_FOUND

        return when (request.httpMethod) {
            HttpMethod.GET -> indexOrShow(request, resource)
            HttpMethod.POST -> resource.controller.create(request)
            else -> {
                LOG.info("ignoring request $request")
                ApiGatewayResponse.NOT_FOUND
            }
        }
    }

    private fun indexOrShow(request: ApiGatewayRequest, resource: Resource) =
            if (request.pathVariables.isEmpty())
                resource.controller.index(request)
            else
                resource.controller.show(request)


    companion object {
        fun defaultRoutes() = mapOf(
                "teams" to Resource(TeamsController(TeamsRepository()))
        )

        private val PATH_REGEX = """^/(\w+)(/.*)?""".toRegex()
        private val LOG = LogManager.getLogger(RequestDispatcher::class.java)
    }
}
