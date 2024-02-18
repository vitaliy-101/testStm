package com.example.teststm.mappers.converters;

import com.example.teststm.dtos.customer.CustomerDto;
import com.example.teststm.dtos.ticket.TicketDto;
import com.example.teststm.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter extends DtoConverter{
    @Autowired
    private TicketDtoConverter ticketDtoConverter;
    public CustomerDto convertToDto(Customer customer){
        List<TicketDto> ticketDtoList = ticketDtoConverter.convertToTicketDto(customer.getTickets());
        return new CustomerDto(customer.getId(), customer.getLogin(), ticketDtoList);
    }
    public List<CustomerDto> convertToDtoList(List<Customer> customers){
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customers){
            customerDtoList.add(convertToDto(customer));
        }
        return customerDtoList;
    }
}
