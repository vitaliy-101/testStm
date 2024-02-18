package com.example.teststm.mappers.converters;

import com.example.teststm.dtos.ticket.TicketDto;
import com.example.teststm.dtos.ticket.TicketDtoIn;
import com.example.teststm.entities.Ticket;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.mappers.converters.DtoConverter;
import com.example.teststm.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketDtoConverter extends DtoConverter {
    @Autowired
    private RouteService routeService;
    public Ticket convertFromDtoIn(TicketDtoIn ticketDtoIn) throws NotFoundByIdException {
        Ticket ticket = new Ticket();
        ticket.setPrice(ticketDtoIn.getPrice());
        ticket.setDate(ticketDtoIn.getDate());
        ticket.setTime(ticketDtoIn.getTime());
        ticket.setSeatNumber(ticketDtoIn.getSeatNumber());
        ticket.setRoute(routeService.getRouteById(ticketDtoIn.getRouteId()));
        return ticket;
    }
    public List<TicketDto> convertToTicketDto(List<Ticket> tickets){
        List<TicketDto> ticketDtoList = new ArrayList<>();
        for (Ticket ticket: tickets){
            ticketDtoList.add(getTicketDto(ticket));
        }
        return ticketDtoList;
    }
    private TicketDto getTicketDto(Ticket ticket){
        TicketDto ticketDto = new TicketDto(ticket.getId(), ticket.getRoute().getStartingPoint(),
                ticket.getRoute().getDestinationPoint(), ticket.getRoute().getDuration(),
                ticket.getRoute().getTransporters().getFirst().getName(),
                ticket.getRoute().getTransporters().getFirst().getPhoneNumber());
        ticketDto.setDate(ticket.getDate());
        ticketDto.setTime(ticket.getTime());
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setSeatNumber(ticket.getSeatNumber());
        return ticketDto;
    }
}
