package de.codekenner.footballscoreboard

import com.amazonaws.services.lambda.runtime.Context
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.charset.StandardCharsets
import java.util.*

interface Request {
    val input: Map<String, Any>
    val context: Context
}

enum class HttpMethod {
    GET, POST, PUT, DELETE
}

data class ApiGatewayRequest(override val input: Map<String, Any>, override val context: Context) : Request {
    val path: String
        get() = input.getOrDefault(PATH, "") as String

    val httpMethod: HttpMethod
        get() = input[HTTP_METHOD]?.let { HttpMethod.valueOf(it.toString().toUpperCase()) }
                ?: throw IllegalArgumentException("Unsupported http method ${input[HTTP_METHOD]}")

    val pathVariables: Map<String, String>
        @Suppress("unchecked_cast")
        get() = input[PATH_PARAMETERS]?.let { it as Map<String, String> } ?: emptyMap()

    val queryParameters: Map<String, String>
        @Suppress("unchecked_cast")
        get() = input[QUERY_PARAMETERS]?.let { it as Map<String, String> } ?: emptyMap()

    companion object {
        // HTTP constants
        const val PATH: String = "path"
        const val HTTP_METHOD: String = "httpMethod"
        const val PATH_PARAMETERS: String = "pathParameters"
        const val QUERY_PARAMETERS: String = "queryStringParameters"
    }
}

data class ApiGatewayResponse(
        val statusCode: Int = 200,
        val body: String? = null,
        val headers: Map<String, String>? = Collections.emptyMap(),
        val isBase64Encoded: Boolean = false
) {

    companion object {
        fun notFound() = build { statusCode = 404 }
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var LOG: Logger = LogManager.getLogger(Builder::class.java)
        var objectMapper: ObjectMapper = ObjectMapper()

        var statusCode: Int = 200
        var rawBody: String? = null
        var headers: Map<String, String>? = Collections.emptyMap()
        var objectBody: Any? = null
        var binaryBody: ByteArray? = null
        var base64Encoded: Boolean = false

        fun build(): ApiGatewayResponse {
            var body: String? = null

            if (rawBody != null) {
                body = rawBody as String
            } else if (objectBody != null) {
                try {
                    body = objectMapper.writeValueAsString(objectBody)
                } catch (e: JsonProcessingException) {
                    LOG.error("failed to serialize object", e)
                    throw RuntimeException(e)
                }
            } else if (binaryBody != null) {
                body = String(Base64.getEncoder().encode(binaryBody), StandardCharsets.UTF_8)
            }
            return ApiGatewayResponse(statusCode, body, headers, base64Encoded)
        }
    }
}
