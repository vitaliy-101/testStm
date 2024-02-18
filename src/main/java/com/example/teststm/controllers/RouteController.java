package com.example.teststm.controllers;

import com.example.teststm.dtos.route.RouteDtoIn;
import com.example.teststm.entities.Route;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.mappers.converters.DtoConverter;
import com.example.teststm.services.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
@RequestMapping("/route")
@Tag(name = "Маршрут", description = "Параметры не должны быть пустыми")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private DtoConverter dtoConverter;
    @Operation(summary = "Добавление нового маршрута", description = "Передаем точку отправления, конечную точку," +
            " длительность в минутах. Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public void createRoute(@Valid @RequestBody RouteDtoIn routeDtoIn){
        routeService.createRoute(dtoConverter.simpleConvert(routeDtoIn, Route.class));
    }
    @Operation(summary = "Обновление данных маршрута по ID", description = "Передаем точку отправления, конечную точку," +
            " длительность в минутах. Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void updateRoute(@Valid @RequestBody RouteDtoIn routeDtoIn, @PathVariable @NotNull Integer id) throws NotFoundByIdException {
        routeService.updateRoute(dtoConverter.simpleConvert(routeDtoIn, Route.class), id);
    }
    @Operation(summary = "Удаление маршрута по ID", description = "Доступно только для Admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable @NotNull Integer id) throws NotFoundByIdException {
        routeService.deleteRoute(id);
    }


}
