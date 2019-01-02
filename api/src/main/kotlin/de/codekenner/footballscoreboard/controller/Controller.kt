package de.codekenner.footballscoreboard.controller

import de.codekenner.footballscoreboard.ApiGatewayRequest
import de.codekenner.footballscoreboard.ApiGatewayResponse

interface Controller {
    fun index(request: ApiGatewayRequest): ApiGatewayResponse = ApiGatewayResponse.NOT_FOUND
    fun show(request: ApiGatewayRequest): ApiGatewayResponse = ApiGatewayResponse.NOT_FOUND
    fun create(request: ApiGatewayRequest): ApiGatewayResponse = ApiGatewayResponse.NOT_FOUND
}