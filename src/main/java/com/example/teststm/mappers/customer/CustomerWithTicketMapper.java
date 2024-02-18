package com.example.teststm.mappers.customer;

import com.example.teststm.entities.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerWithTicketMapper implements ResultSetExtractor<List<Customer>> {

    @Override
    public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Customer> allCustomers = new HashMap<>();
        while (rs.next()){
            Integer customerId = rs.getInt("cust_id");
            Customer customer = allCustomers.get(customerId);
            if (customer == null){
                Role role = Role.valueOf(rs.getString("role"));
                customer = new Customer(customerId, rs.getString("login"),
                        rs.getString("password"), rs.getString("fio"), role);
                allCustomers.put(customerId, customer);
            }
            //TODO
            if (rs.getInt("ticket_id") != 0){
                Ticket ticket = new Ticket(rs.getInt("ticket_id"),
                        rs.getInt("seat_number"), rs.getDate("date").toLocalDate(), rs.getString("time"),
                        rs.getInt("price"));
                Route route = new Route(rs.getInt("route_id"), rs.getString("starting_point"),
                        rs.getString("destination_point"), rs.getInt("duration"));
                route.addTransporter(new Transporter(rs.getInt("transp_id"), rs.getString("name"),
                        rs.getString("phone_number")));
                ticket.setRoute(route);
                customer.addTicket(ticket);
            }

        }

        return allCustomers.values().stream().toList();
    }
}
