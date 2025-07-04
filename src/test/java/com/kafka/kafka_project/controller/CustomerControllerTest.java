package com.kafka.kafka_project.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.kafka.kafka_project.model.Customer;
import com.kafka.kafka_project.service.KafkaProducerService;
import com.kafka.kafka_project.service.SnowflakeService;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SnowflakeService snowflakeService;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = Customer.builder()
            .customerId(1L)
            .firstName("Test")
            .build();

        when(snowflakeService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/api/customers/fetch/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Test"));
    }
}
