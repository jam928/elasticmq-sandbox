package com.sandbox.elasticmq.config

import com.sandbox.elasticmq.ElasticMqApplication
import org.elasticmq.rest.sqs.SQSRestServer
import org.elasticmq.rest.sqs.SQSRestServerBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.lang.Exception
import javax.annotation.PostConstruct

@Configuration
class ElasticMqConfig {
    private var sqsRestServer: SQSRestServer? = null
    private val logger: Logger = LoggerFactory.getLogger(ElasticMqConfig::class.java)

    @Value("\${elasticMqLocalSqs.enable}")
    private var enableLocalMq: Boolean = false

    @PostConstruct
    fun enableElasticMq() {
        if(!enableLocalMq) {
            return
        }
        // start elastic mq server
        try {
            sqsRestServer = SQSRestServerBuilder
                .withPort(9324)
                .withInterface("localhost")
                .start()
            logger.info("Elastic mq server started...")
        } catch (e: Exception) {
            logger.error("Exception occured while starting elastic mq server: " + e.localizedMessage)
            return
        }
    }
}