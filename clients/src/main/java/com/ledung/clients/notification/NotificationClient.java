package com.ledung.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification", url = "${clients.notification.url}")
public interface NotificationClient {
    @PostMapping(path = "api/v1/notification")
    void sendNotification(NotificationRequest request);
}
