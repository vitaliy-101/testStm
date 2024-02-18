package com.example.teststm.dtos.customer;

import com.example.teststm.dtos.ticket.TicketDto;
import com.example.teststm.dtos.ticket.TicketMinDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
    private String login;
    private List<TicketDto> tickets;

}
