package de.codekenner.footballscoreboard.routing

import de.codekenner.footballscoreboard.ApiGatewayRequest
import de.codekenner.footballscoreboard.ApiGatewayResponse

interface Controller {
    fun index(request: ApiGatewayRequest): ApiGatewayResponse = ApiGatewayResponse.notFound()
    fun show(request: ApiGatewayRequest): ApiGatewayResponse = ApiGatewayResponse.notFound()
}