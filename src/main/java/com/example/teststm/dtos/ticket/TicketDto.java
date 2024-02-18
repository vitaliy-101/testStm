package com.example.teststm.dtos.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketDto extends TicketBaseDto{
    private Integer id;
    private String startingPoint;
    private String destinationPoint;
    private Integer duration;
    private String transporterName;
    private String transporterPhoneNumber;
}
