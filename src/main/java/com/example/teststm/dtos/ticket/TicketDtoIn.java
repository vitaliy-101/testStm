package com.example.teststm.dtos.ticket;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDtoIn extends TicketBaseDto{
    @Min(value = 1, message = "the routeId must be greater than zero")
    private Integer routeId;
//    @Min(value = 1, message = "the customerId must be greater than zero")
//    private Integer customerId;

}
