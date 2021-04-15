package com.sandbox.elasticmq.controller

import com.sandbox.elasticmq.pojos.Quote
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.fasterxml.jackson.databind.ObjectMapper

@RestController
class QuoteController (@Autowired private val queueMessagingTemplate: QueueMessagingTemplate) {

    @Value("\${aws.sqsQueueName}")
    private val queueName: String = ""

    @PostMapping( "/quote")
    fun saveQuote(@RequestBody quote: Quote): String {
        queueMessagingTemplate.convertAndSend(queueName, quote)
        return ResponseEntity.ok().toString()
    }
}