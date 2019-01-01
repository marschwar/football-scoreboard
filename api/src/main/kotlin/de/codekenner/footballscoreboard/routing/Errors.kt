package de.codekenner.footballscoreboard.routing

open class ApiException(val code: Int = 500, message: String = "Internal Server Error") : Exception(message)

class InvalidArguments(body: String) : ApiException(400, "The entity $body doesn't contain all the required fields")

class NotFound(resource: String) : ApiException(404, "The route/resource $resource doesn't exist")
