package com.sandbox.elasticmq.consumer

import com.sandbox.elasticmq.pojos.Quote
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import kotlin.math.log

@Component
@Slf4j
@EnableSqs
class QuoteConsumer {

    private val logger: Logger = LoggerFactory.getLogger(QuoteConsumer::class.java)

    @SqsListener("sqs-queue-name")
    fun receiveMessage(message: Message<Quote>) {
        logger.info("Quote message body: ${message.payload}")
    }
}