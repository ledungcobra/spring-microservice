package com.ledung.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;


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
        var response = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class, Map.of("customerId", persistedCustomer.getId()));
        if (response != null && response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // todo: send notification

    }
}
