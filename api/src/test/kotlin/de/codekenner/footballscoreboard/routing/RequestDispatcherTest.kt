package de.codekenner.footballscoreboard.routing

import com.amazonaws.services.lambda.runtime.Context
import com.nhaarman.mockitokotlin2.mock
import de.codekenner.footballscoreboard.ApiGatewayResponse
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test

internal class RequestDispatcherTest : StringSpec({

    "unknown resource should result in not found" {
        val context: Context = mock()
        val response = RequestDispatcher(emptyMap()).handleRequest(mapOf("path" to "/foo"), context)

        response shouldBe ApiGatewayResponse.notFound()
    }
})