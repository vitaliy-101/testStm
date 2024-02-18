package com.example.teststm.services;

import com.example.teststm.dtos.ticket.TicketFilterDtoIn;
import com.example.teststm.entities.Customer;
import com.example.teststm.entities.Route;
import com.example.teststm.entities.Ticket;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository repository;
    public void createTicket(Ticket ticket){
        repository.save(ticket);
    }
    public List<Ticket> getAllTickets(TicketFilterDtoIn ticketFilterDtoIn){
        return repository.findAll(ticketFilterDtoIn);
    }
    public void updateTicket(Ticket ticket, Integer id) throws NotFoundByIdException {
        if (!repository.existById(id)){
            throw new NotFoundByIdException(Ticket.class, id);
        }
        repository.update(ticket, id);
    }
    public void deleteTicket(Integer id) throws NotFoundByIdException {
        if (!repository.existById(id)){
            throw new NotFoundByIdException(Ticket.class, id);
        }
        repository.delete(id);
    }

}
