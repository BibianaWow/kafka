package com.kafka.kafka_project.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.kafka_project.model.Customer;
import com.kafka.kafka_project.repository.CustomerMongoRepository;
import com.kafka.kafka_project.service.KafkaProducerService;
import com.kafka.kafka_project.service.SnowflakeService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private SnowflakeService snowflakeService;

    @Autowired
    private KafkaProducerService kafkaProducer;

    @Autowired
    private CustomerMongoRepository mongoRepository;

    @GetMapping("/")
    public List<Customer> listCustomers(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) throws SQLException {
        return snowflakeService.getPaginatedCustomers(page, size);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<String> fetchAndSend(@PathVariable Long id) throws SQLException, JsonProcessingException {
        Customer customer = snowflakeService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        kafkaProducer.send(customer);
        return ResponseEntity.ok("Customer sent to Kafka.");
    }

    @GetMapping("/mongo")
    public List<Customer> getAllFromMongo() {
        return mongoRepository.findAll();
    }
}

