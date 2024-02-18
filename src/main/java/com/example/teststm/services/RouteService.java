package com.example.teststm.services;

import com.example.teststm.entities.Customer;
import com.example.teststm.entities.Route;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RouteService {
    @Autowired
    private RouteRepository repository;


    public Route getRouteById(Integer id) throws NotFoundByIdException {
        if (!repository.existById(id))
        {
            throw new NotFoundByIdException(Route.class, id);
        }

        return repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Route.class, id));
    }
    public void createRoute(Route route){
        repository.save(route);
    }
    public void updateRoute(Route route, Integer id) throws NotFoundByIdException {
        if (!repository.existById(id)){
            throw new NotFoundByIdException(Route.class, id);
        }
        repository.update(route, id);

    }
    public void deleteRoute(Integer id) throws NotFoundByIdException {
        if (!repository.existById(id)){
            throw new NotFoundByIdException(Route.class, id);
        }
        repository.delete(id);
    }

}
