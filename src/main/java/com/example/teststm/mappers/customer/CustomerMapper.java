package com.example.teststm.mappers.customer;

import com.example.teststm.entities.Customer;
import com.example.teststm.entities.Role;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Role role = Role.valueOf(resultSet.getString("role"));
        return new Customer(resultSet.getInt("id"), resultSet.getString("login"),
                resultSet.getString("password"), resultSet.getString("fio"), role);
    }
}
