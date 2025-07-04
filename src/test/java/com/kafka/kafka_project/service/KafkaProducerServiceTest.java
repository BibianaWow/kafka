package com.kafka.kafka_project.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.kafka_project.model.Customer;
import org.mockito.junit.jupiter.MockitoExtension;
 

@ExtendWith(MockitoExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService producerService;

    @Test
    void testSendCustomerMessage() throws JsonProcessingException {
        Customer customer = Customer.builder().customerId(1L).firstName("Alice").build();

        producerService.send(customer);

        verify(kafkaTemplate, times(1)).send(anyString(), anyString());
    }
}

