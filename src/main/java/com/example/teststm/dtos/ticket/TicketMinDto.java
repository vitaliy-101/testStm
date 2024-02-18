package com.example.teststm.dtos.ticket;

import com.example.teststm.dtos.route.RouteMinDto;
import com.example.teststm.entities.Route;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketMinDto extends TicketBaseDto{
    private Integer id;
    private RouteMinDto route;
}
