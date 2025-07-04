package com.kafka.kafka_project.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.sql.DataSource;
import java.sql.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kafka.kafka_project.model.Customer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SnowflakeServiceTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private SnowflakeService snowflakeService;

    @Test
    public void testGetCustomerById() throws Exception {
        // Mocks encadenados para simular JDBC
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("C_CUSTOMER_ID")).thenReturn(1L);
        when(resultSet.getString("C_FIRST_NAME")).thenReturn("John");

        Customer customer = snowflakeService.getCustomerById(1L);

        assertNotNull(customer);
        assertEquals(1L, customer.getCustomerId());
        assertEquals("John", customer.getFirstName());

        verify(preparedStatement, times(1)).executeQuery();
    }
}