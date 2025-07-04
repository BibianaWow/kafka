package com.kafka.kafka_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "customer_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Customer {
    @Id
    private String id;

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private Address address;
   
    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.birthDay = builder.birthDay;
        this.birthMonth = builder.birthMonth;
        this.birthYear = builder.birthYear;
        this.address = builder.address;
    }
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getBirthDay() {
		return birthDay;
	}


	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}


	public int getBirthMonth() {
		return birthMonth;
	}


	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}


	public int getBirthYear() {
		return birthYear;
	}


	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Customer(String id, Long customerId, String firstName, String lastName, String email, int birthDay,
			int birthMonth, int birthYear, Address address) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		this.address = address;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerId=" + customerId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", birthDay=" + birthDay + ", birthMonth=" + birthMonth
				+ ", birthYear=" + birthYear + ", address=" + address + "]";
	}
	public static Builder builder() {
        return new Builder();
    }
	public Builder toBuilder() {
	    return new Builder()
	        .customerId(this.customerId)
	        .firstName(this.firstName)
	        .lastName(this.lastName)
	        .email(this.email)
	        .birthDay(this.birthDay)
	        .birthMonth(this.birthMonth)
	        .birthYear(this.birthYear)
	        .address(this.address);
	}
	public static class Builder {
        private Long customerId;
        private String firstName;
        private String lastName;
        private String email;
        private int birthDay;
        private int birthMonth;
        private int birthYear;
        private Address address;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder birthDay(int birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public Builder birthMonth(int birthMonth) {
            this.birthMonth = birthMonth;
            return this;
        }

        public Builder birthYear(int birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}


