package com.ledung.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Notification {
    public static final String NOTIFICATION_ID_SEQUENCE = "notification_id_sequence";

    @Id
    @SequenceGenerator(name = NOTIFICATION_ID_SEQUENCE, sequenceName = NOTIFICATION_ID_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = NOTIFICATION_ID_SEQUENCE)
    private Integer id;
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;
}
