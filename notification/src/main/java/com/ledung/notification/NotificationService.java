package com.ledung.notification;

import com.ledung.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(Notification.builder()
                .sender("ledung")
                .sentAt(LocalDateTime.now())
                .message(notificationRequest.message())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .toCustomerId(notificationRequest.toCustomerId())
                .build());
    }
}
