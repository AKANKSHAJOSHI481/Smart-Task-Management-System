package com.stm.smart_task_management.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Async
    public void sendTaskCreatedEmail(String email){
        try {
            Thread.sleep(5000);
            log.info("Email sent to {}", email);
        } catch (Exception e) {
            log.error("EMAIL FAILED");
        }
    }
}
