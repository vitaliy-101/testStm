package com.example.teststm.services;

import com.example.teststm.entities.Role;
import com.example.teststm.entities.Route;
import com.example.teststm.entities.Ticket;
import com.example.teststm.exceptions.ExistsByLoginException;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.repository.CustomerRepository;
import com.example.teststm.entities.Customer;
import com.example.teststm.repository.TicketRepository;
import com.example.teststm.validation.ValidationErrorResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private TicketRepository ticketRepository;
    public Customer getCustomerById(Integer id) throws NotFoundByIdException {
        return repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Customer.class, id));
    }
    public Customer getCurrentCustomer(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return getCustomerByLogin(login);
    }
    public Customer getCustomerByLogin(String login){
        return repository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public List<Customer> getCustomers(){
        return repository.findAll();
    }
    public void createCustomer(Customer customer) throws ExistsByLoginException {
        if (repository.existByLogin(customer.getLogin())){
            throw new ExistsByLoginException(Customer.class, customer.getLogin());
        }
        repository.save(customer);
    }
    public void setCustomerRole(Role role, Integer id) throws NotFoundByIdException {
        if (!repository.existById(id)){
            throw new NotFoundByIdException(Customer.class, id);
        }
        repository.setRole(role, id);

    }

    public void buyTicket(Integer ticketId) throws NotFoundByIdException {
        if (!ticketRepository.existById(ticketId) || ticketRepository.findCustomerId(ticketId) != null){
            throw new NotFoundByIdException(Ticket.class, ticketId);
        }
        repository.buy(ticketId, getCurrentCustomer().getId());
    }
    public Customer getCustomerTickets(){
        return repository.findByIdWithTickets(getCurrentCustomer().getId());
    }


}
