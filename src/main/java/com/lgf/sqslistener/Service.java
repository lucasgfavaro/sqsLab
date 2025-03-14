package com.lgf.sqslistener;


import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@Slf4j
public class Service {

    public void doSomething() {
        try {
            log.info("Processing task...");
            Thread.sleep(50000); // Simulating long processing
        } catch (InterruptedException e) {
            log.warn("Task was interrupted!");
            Thread.currentThread().interrupt(); // Restore interrupt state
        }
        log.info("Task completed.");
    }

}
