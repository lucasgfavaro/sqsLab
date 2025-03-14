package com.lgf.sqslistener;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.ListenerMode;
import io.awspring.cloud.sqs.listener.QueueNotFoundStrategy;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class ConfigSqsFactory {

    @Bean("sqsFactory")
    SqsMessageListenerContainerFactory defaultSqsListenerContainerFactory(SqsAsyncClient sqsAsyncClient) {

        return SqsMessageListenerContainerFactory.builder().configure(
                        options -> options.acknowledgementMode(AcknowledgementMode.MANUAL)
                                .listenerMode(ListenerMode.SINGLE_MESSAGE)
                                .maxConcurrentMessages(1)
                                .maxMessagesPerPoll(1)
                                .listenerShutdownTimeout(Duration.of(30L, ChronoUnit.SECONDS))
                                .acknowledgementShutdownTimeout(Duration.of(30L, ChronoUnit.SECONDS))
                                .acknowledgementThreshold(5)
                                .acknowledgementInterval(Duration.of(50, ChronoUnit.MILLIS))
                                .queueNotFoundStrategy(QueueNotFoundStrategy.FAIL)
                ).sqsAsyncClient(sqsAsyncClient)
                .build();
    }

}
