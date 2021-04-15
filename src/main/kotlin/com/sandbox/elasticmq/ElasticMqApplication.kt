package com.sandbox.elasticmq

import com.sandbox.elasticmq.config.SqsConfig
import org.elasticmq.rest.sqs.SQSRestServer
import org.elasticmq.rest.sqs.SQSRestServerBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.lang.Exception
import javax.annotation.PostConstruct

@SpringBootApplication
class ElasticMqApplication

fun main(args: Array<String>) {
    runApplication<ElasticMqApplication>(*args)
}

