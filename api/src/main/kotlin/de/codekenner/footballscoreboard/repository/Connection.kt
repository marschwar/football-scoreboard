package de.codekenner.footballscoreboard.repository

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import org.apache.logging.log4j.LogManager

object Connection {
    private val LOG = LogManager.getLogger(Connection::class.java)

    private val client: AmazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.EU_CENTRAL_1)
            .build()

    fun dynamoDB(): DynamoDB = DynamoDB(client)

}