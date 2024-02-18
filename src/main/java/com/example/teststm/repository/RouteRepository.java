package com.example.teststm.repository;

import com.example.teststm.entities.Customer;
import com.example.teststm.entities.Role;
import com.example.teststm.entities.Route;
import com.example.teststm.entities.Ticket;
import com.example.teststm.mappers.customer.CustomerMapper;
import com.example.teststm.mappers.route.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RouteRepository {
    @Autowired
    private JdbcTemplate template;
    public RouteRepository(JdbcTemplate template){
        this.template = template;
    }
    public void save(Route route){
        template.update("INSERT INTO route (starting_point, destination_point, duration) VALUES(?, ?, ?)",
                route.getStartingPoint(), route.getDestinationPoint(), route.getDuration());
    }
    public Boolean existById(Integer id){
        Route route = template.query("SELECT * FROM route WHERE id=?", new Object[]{id}, new RouteMapper()).stream().findAny().orElse(null);
        if (route == null){
            return false;
        }
        return true;
    }
    public void update(Route route, Integer id){
        template.update("UPDATE route SET starting_point = ?, destination_point = ?," +
                "duration = ? WHERE id = ?", route.getStartingPoint(), route.getDestinationPoint(),
                route.getDuration(), id);
    }
    public Optional<Route> findById(Integer id){
        return Optional.of(template.query("SELECT * FROM route WHERE id=?", new Object[]{id}, new RouteMapper()).getFirst());
    }

    public void delete(Integer id){
        template.update("DELETE FROM route WHERE id = ?", id);
    }
}
