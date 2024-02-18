package com.example.teststm.mappers.converters;

import com.example.teststm.dtos.transporter.TransporterDtoIn;
import com.example.teststm.entities.Route;
import com.example.teststm.entities.Transporter;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.mappers.converters.DtoConverter;
import com.example.teststm.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransporterDtoConverter extends DtoConverter {
    @Autowired
    private RouteService routeService;
    public Transporter convertFromDtoIn(TransporterDtoIn transporterDtoIn) throws NotFoundByIdException {
        Transporter transporter = simpleConvert(transporterDtoIn, Transporter.class);
        if (transporterDtoIn.getRouteIds().isEmpty()){
            throw new NotFoundByIdException(Route.class, null);
        }
        List<Route> routes = new ArrayList<>();
        for (Integer id : transporterDtoIn.getRouteIds()){
            routes.add(routeService.getRouteById(id));
        }
        transporter.setRoutes(routes);
        return transporter;
    }

}
