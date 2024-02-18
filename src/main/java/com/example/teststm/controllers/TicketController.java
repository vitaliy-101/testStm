package com.example.teststm.controllers;

import com.example.teststm.dtos.ticket.TicketDto;
import com.example.teststm.dtos.ticket.TicketDtoIn;
import com.example.teststm.dtos.ticket.TicketFilterDtoIn;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.mappers.converters.TicketDtoConverter;
import com.example.teststm.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@Validated
@Tag(name = "Билет", description = "Параметры не должны быть пустыми")
public class TicketController {
    @Autowired
    private TicketService service;
    @Autowired
    private TicketDtoConverter converter;
    @Operation(summary =  "Добавление нового билета", description = "Передаем дату в формате YYYY-MM-DD, время в формате HH:MM," +
            "номер места в автобусе, ID маршрута. Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public void createTicket(@Valid @RequestBody TicketDtoIn ticket) throws NotFoundByIdException {
        service.createTicket(converter.convertFromDtoIn(ticket));
    }
    @Operation(summary =  "Обновление данных билета по ID", description = "Передаем дату в формате YYYY-MM-DD, время в формате HH:MM," +
            "номер места в автобусе, ID маршрута. Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void updateTicket(@Valid @RequestBody  TicketDtoIn ticket, @PathVariable @NotNull Integer id) throws NotFoundByIdException {
        service.updateTicket(converter.convertFromDtoIn(ticket), id);
    }
    @Operation(summary =  "Удаление билета по ID. Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable @NotNull Integer id) throws NotFoundByIdException {
        service.deleteTicket(id);
    }


    @Operation(summary =  "Получение всех доступных для покупке билетов", description = "Пагинация и фильтрация " +
            "данных: номер страницы, количество билетов на странице, дата в формате YYYY-MM-DD, " +
            "время в формате HH:MM, точка отправления, конечная точка, имя перевозчика. Доступно для покупателей")
    @GetMapping
    public List<TicketDto> getAllTickets(
            @RequestParam(required = false, defaultValue = "1")
            @Min(value = 1, message = "the page must be greater than zero")
            Integer page,
            @RequestParam(required = false, defaultValue = "10")
            @Min(value = 1, message = "the size must be greater than zero")
            Integer size,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,
            @RequestParam(required = false)
            @Pattern(regexp = "(^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$)", message = "incorrect time format")
            String time,
            @RequestParam(required = false)
            String startingPoint,
            @RequestParam(required = false)
            String destinationPoint,
            @RequestParam(required = false)
            String transporterName

    ){
        return converter.convertToTicketDto(service.getAllTickets(new TicketFilterDtoIn(page, size, date, time, startingPoint, destinationPoint,
                transporterName)));
    }

}
