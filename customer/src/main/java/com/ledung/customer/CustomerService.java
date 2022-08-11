package com.ledung.customer;

import com.ledung.clients.fraud.FraudClient;
import com.ledung.clients.notification.NotificationClient;
import com.ledung.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        // todo: check if email is valid
        // todo: check if email not taken
        // todo: check if fraudster customer

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        var persistedCustomer = customerRepository.saveAndFlush(customer);
        var response = fraudClient.isFraud(persistedCustomer.getId());
        if (response != null && response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // todo: send notification
        notificationClient.sendNotification(
                new NotificationRequest(persistedCustomer.getId(), persistedCustomer.getEmail(),
                        String.format("Hi %s welcome to microservice app", persistedCustomer.getFirstName())));

    }
}
