package com.example.teststm.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    private Integer id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String time;
    private Integer seatNumber;
    private Integer price;
    private Customer customer;
    private Route route;
    public Ticket(Integer id, Integer seatNumber, LocalDate date, String time, Integer price){
        this.id = id;
        this.seatNumber = seatNumber;
        this.date = date;
        this.time = time;
        this.price = price;
    }

}
