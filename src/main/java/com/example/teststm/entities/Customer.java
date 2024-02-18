package com.example.teststm.entities;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    private Integer id;
    private String login;
    private String password;
    private String fio;
    private Role role;
    private List<Ticket> tickets = new ArrayList<>();
    public Customer(Integer id, String login, String password, String fio, Role role){
        this.id = id;
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.role = role;
    }
    public Customer(String login, String password, String fio){
        this.login = login;
        this.password = password;
        this.fio = fio;
    }
    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }
}
