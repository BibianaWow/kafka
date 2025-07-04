package com.kafka.kafka_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.kafka_project.model.Address;
import com.kafka.kafka_project.model.Customer;

@Service
public class SnowflakeService {

    @Autowired
    private DataSource dataSource;

    public List<Customer> getPaginatedCustomers(int page, int size) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM CUSTOMER LIMIT ? OFFSET ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, size);
            ps.setInt(2, page * size);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        }

        return customers;
    }

    public Customer getCustomerById(Long id) throws SQLException {
        String query = """
            SELECT c.c_customer_sk, c.c_first_name, c.c_last_name, c.c_email_address,
                   c.c_birth_day, c.c_birth_month, c.c_birth_year,
                   a.ca_street_name, a.ca_street_number, a.ca_city, a.ca_state, a.ca_country
            FROM CUSTOMER c
            LEFT JOIN CUSTOMER_ADDRESS a ON c.c_current_addr_sk = a.ca_address_sk
            WHERE c.c_customer_sk = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapCustomerWithAddress(rs);
            }
        }

        return null;
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        return Customer.builder()
                .customerId(rs.getLong("c_customer_sk"))
                .firstName(rs.getString("c_first_name"))
                .lastName(rs.getString("c_last_name"))
                .email(rs.getString("c_email_address"))
                .birthDay(rs.getInt("c_birth_day"))
                .birthMonth(rs.getInt("c_birth_month"))
                .birthYear(rs.getInt("c_birth_year"))
                .build();
    }

    private Customer mapCustomerWithAddress(ResultSet rs) throws SQLException {
        Address address = Address.builder()
                .street(rs.getString("ca_street_name"))
                .number(rs.getString("ca_street_number"))
                .city(rs.getString("ca_city"))
                .state(rs.getString("ca_state"))
                .country(rs.getString("ca_country"))
                .build();

        return mapCustomer(rs).toBuilder().address(address).build();
    }
}
