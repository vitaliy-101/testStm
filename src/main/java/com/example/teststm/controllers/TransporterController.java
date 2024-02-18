package com.example.teststm.controllers;

import com.example.teststm.dtos.transporter.TransporterDtoIn;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.mappers.converters.TransporterDtoConverter;
import com.example.teststm.services.TransporterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transporter")
@Tag(name = "Перевозчик", description = "Параметры не должны быть пустыми")
public class TransporterController {
    @Autowired
    private TransporterService transporterService;
    @Autowired
    private TransporterDtoConverter dtoConverter;

    @Operation(summary = "Добавление нового перевозчика", description = "Передаем имя, номер телефона и ID маршрутов," +
            " которые имеются у перевозчика. Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public void createTransporter(@Valid @RequestBody TransporterDtoIn transporter) throws NotFoundByIdException {
        transporterService.createTransporter(dtoConverter.convertFromDtoIn(transporter));
    }
    @Operation(summary = "Изменение данных о перевозчике по его ID", description = "Передаем имя, номер телефона и ID маршрутов, " +
            "которые имеются у перевозчика. Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void updateTransporter(@Valid @RequestBody TransporterDtoIn transporter, @PathVariable @NotNull Integer id) throws NotFoundByIdException {
        transporterService.updateTransporter(dtoConverter.convertFromDtoIn(transporter), id);
    }
    @Operation(summary = "Удаление данных о перевозчике по его ID. Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTransporter(@PathVariable @NotNull Integer id) throws NotFoundByIdException {
        transporterService.deleteTransporter(id);
    }

}
