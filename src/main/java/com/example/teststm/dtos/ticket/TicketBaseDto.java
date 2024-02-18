package com.example.teststm.dtos.ticket;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class TicketBaseDto {
    @NotNull(message = "incorrect date format")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @Pattern(regexp = "(^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$)", message = "incorrect time format")
    private String time;
    @Min(value = 1, message = "the seat number must be greater than zero")
    private Integer seatNumber;
    @Min(value = 1, message = "the price must be greater than zero")
    private Integer price;
}
