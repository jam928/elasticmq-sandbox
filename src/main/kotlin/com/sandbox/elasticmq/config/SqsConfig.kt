package com.sandbox.elasticmq.config

import org.springframework.context.annotation.Configuration
import com.amazonaws.auth.AWSStaticCredentialsProvider

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration

import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.*

import com.sandbox.elasticmq.consumer.QuoteConsumer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.elasticmq.rest.sqs.SQSRestServer
import org.elasticmq.rest.sqs.SQSRestServerBuilder
import java.lang.Exception
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler
import org.springframework.context.annotation.Primary
import javax.annotation.PostConstruct


@Configuration
class SqsConfig {

    @Value("\${aws.accessKey}")
    private var awsAccessKey: String = ""

    @Value("\${aws.secretKey}")
    private var awsSecretKey: String = ""

    @Value("\${aws.endpoint}")
    private var awsEndpoint: String = ""

    @Value("\${aws.region}")
    private var awsRegion: String = ""

    @Value("\${elasticMqLocalSqs.endpoint}")
    private var localMqEndpoint: String = ""

    @Value("\${elasticMqLocalSqs.enable}")
    private var enableLocalMq: Boolean = false

    @Value("\${aws.sqsQueueName}")
    private val sqsQueueName: String = ""

    private val logger: Logger = LoggerFactory.getLogger(SqsConfig::class.java)

    @Bean
    @Primary
    fun amazonSQSAsync(): AmazonSQSAsync {
        val endpoint = if(enableLocalMq) localMqEndpoint else awsEndpoint
        return AmazonSQSAsyncClientBuilder
            .standard()
            .withCredentials(
                AWSStaticCredentialsProvider(
                    BasicAWSCredentials(awsAccessKey, awsSecretKey)
                )
            ).withEndpointConfiguration(EndpointConfiguration(endpoint, awsRegion))
            .build()
    }

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate {
        if(enableLocalMq) {
            val queueUrl = amazonSQSAsync().createQueue(sqsQueueName).queueUrl
            logger.info("Local Queue url: ${queueUrl}")
        }
        return QueueMessagingTemplate(amazonSQSAsync())
    }


}