package com.example.teststm.mappers.route;

import com.example.teststm.entities.Route;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RouteMapper implements RowMapper<Route> {
    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Route(rs.getInt("id"), rs.getString("starting_point"),
                rs.getString("destination_point"), rs.getInt("duration"));
    }
}
