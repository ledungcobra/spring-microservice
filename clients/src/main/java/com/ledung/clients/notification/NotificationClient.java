package com.ledung.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notification")
public interface NotificationClient {
    @PostMapping(path = "api/v1/notification")
    void sendNotification(NotificationRequest request);
}
