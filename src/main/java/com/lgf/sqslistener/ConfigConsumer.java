package com.lgf.sqslistener;


import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ConfigConsumer {


    @SqsListener(value = "lucasqueue", factory = "sqsFactory")
    public void listen(Message<String> s3EventNotificationMessage, Acknowledgement acknowledgement) {
        log.info("Start Received S3 Event Notification: {}", s3EventNotificationMessage.getPayload());

        try {
            Thread.sleep(15000L);
            log.info("business process finished....");
        } catch (Exception ex) {
            log.error("Failed.", ex);
        } finally {
            acknowledgement.acknowledgeAsync();
            log.info("acknowledgment finish  ....");
        }
    }
}