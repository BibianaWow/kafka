package com.kafka.kafka_project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kafka.kafka_project.model.Customer;

@Repository
public interface CustomerMongoRepository extends MongoRepository<Customer, String> {
}
