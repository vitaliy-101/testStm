package com.example.teststm.controllers;


import com.example.teststm.dtos.customer.CustomerDto;
import com.example.teststm.entities.Role;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.mappers.converters.CustomerConverter;
import com.example.teststm.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/customer")
@Tag(name = "Пользователь", description = "Параметры не должны быть пустыми")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private CustomerConverter converter;

    @Operation(summary = "Покупка пользователем билета по ID билета", description = "Передаем ID билета. Доступно и покупателям")
    @PostMapping("/buy-ticket/{ticketId}")
    public void buyTicket(@PathVariable @NotNull Integer ticketId) throws NotFoundByIdException {
        service.buyTicket(ticketId);
    }
    @Operation(summary = "Просмотр билетов, купленным данным пользователем", description = "Доступно и покупателям")
    @GetMapping("/customer-tickets")
    public CustomerDto getCustomerTickets()  {
        return converter.convertToDto(service.getCustomerTickets());
    }
    @Operation(summary = "Просмотр всех пользователей и их билетов", description = "Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/all-customers")
    public List<CustomerDto> getAllCustomers(){
        return converter.convertToDtoList(service.getCustomers());
    }
    @Operation(summary = "Изменение роли пользователя по ID пользователя", description = "Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/role")
    public void setCustomerRole( Role role,
                                @NotNull Integer id) throws NotFoundByIdException {
        service.setCustomerRole(role, id);
    }







}
