package com.kafka.kafka_project;

import org.springframework.boot.SpringApplication;

public class TestKafkaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.from(KafkaProjectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
