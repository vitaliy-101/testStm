package com.example.teststm.repository;

import com.example.teststm.entities.Customer;
import com.example.teststm.entities.Role;
import com.example.teststm.mappers.customer.CustomerMapper;
import com.example.teststm.mappers.customer.CustomerWithTicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    @Autowired
    private JdbcTemplate template;

    public CustomerRepository(JdbcTemplate template){
        this.template = template;
    }
    public void save(Customer customer){
        template.update("INSERT INTO customer (login, password, fio, role) VALUES(?, ?, ?, ?)", customer.getLogin(),
                customer.getPassword(), customer.getFio(), customer.getRole().toString());
    }
    public void buy(Integer ticketId, Integer customerId){
        template.update("UPDATE ticket SET customer_id = ? WHERE id = ?", customerId, ticketId);
    }
    public void setRole(Role role, Integer id){
        template.update("UPDATE customer SET role = ? WHERE id = ?", role.toString(), id);
    }
    public Boolean existByLogin(String login){
        Customer customer = template.query("SELECT * FROM customer WHERE login=?", new Object[]{login}, new CustomerMapper()).stream().findAny().orElse(null);
        if (customer == null){
            return false;
        }
        return true;
    }
    public Boolean existById(Integer id){
        Customer customer = template.query("SELECT * FROM customer WHERE id=?", new Object[]{id}, new CustomerMapper()).stream().findAny().orElse(null);
        if (customer == null){
            return false;
        }
        return true;
    }

    public Optional<Customer> findByLogin(String login){
        return Optional.of(template.query("SELECT * FROM customer WHERE login=?", new Object[]{login}, new CustomerMapper()).getFirst());
    }

    public Optional<Customer> findById(Integer id){
        return Optional.of(template.query("SELECT * FROM customer WHERE id=?", new Object[]{id}, new CustomerMapper()).getFirst());
    }
    public Customer findByIdWithTickets(Integer id){
        return template.query("SELECT customer.id AS cust_id, login, password, fio, role, ticket.id AS ticket_id," +
                "seat_number, price, date, time, route.id as route_id, starting_point, " +
                "destination_point, duration, route_transporter.transporter_id AS transp_id," +
                "name, phone_number FROM customer " +
                "left join ticket on customer.id = ticket.customer_id " +
                "left join route on ticket.route_id = route.id " +
                "left join route_transporter on route.id = route_transporter.route_id " +
                "left join transporter on route_transporter.transporter_id = transporter.id"
                + " WHERE customer.id = ?", new Object[]{id}, new CustomerWithTicketMapper()).getFirst();

    }

    public List<Customer> findAll(){
        return template.query("SELECT customer.id AS cust_id, login, password, fio, role, ticket.id AS ticket_id," +
                "seat_number, price, date, time, route.id as route_id, starting_point, " +
                "destination_point, duration, route_transporter.transporter_id AS transp_id," +
                "name, phone_number FROM customer " +
                "left join ticket on customer.id = ticket.customer_id " +
                "left join route on ticket.route_id = route.id " +
                "left join route_transporter on route.id = route_transporter.route_id " +
                "left join transporter on route_transporter.transporter_id = transporter.id", new CustomerWithTicketMapper());
    }
}
