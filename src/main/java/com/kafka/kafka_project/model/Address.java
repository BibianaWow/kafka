package com.kafka.kafka_project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String street;
    private String number;
    private String city;
    private String state;
    private String country;
    
    private Address(Builder builder) {
        this.street = builder.street;
        this.number = builder.number;
        this.city = builder.city;
        this.state = builder.state;
        this.country = builder.country;
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String street;
        private String number;
        private String city;
        private String state;
        private String country;

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
	
}
