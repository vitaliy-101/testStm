package com.example.teststm.mappers.ticket;

import com.example.teststm.entities.Customer;
import com.example.teststm.entities.Route;
import com.example.teststm.entities.Ticket;
import com.example.teststm.entities.Transporter;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketsMapper  implements ResultSetExtractor<List<Ticket>> {
    @Override
    public List<Ticket> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Ticket> allTickets = new ArrayList<>();
        while (rs.next()){
            Ticket ticket = new Ticket(rs.getInt("ticket_id"),
                    rs.getInt("seat_number"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("time"),
                    rs.getInt("price"));

            Route route = new Route(rs.getInt("route_id"), rs.getString("starting_point"),
                    rs.getString("destination_point"), rs.getInt("duration"));
            route.addTransporter(new Transporter(rs.getInt("transp_id"), rs.getString("name"),
                    rs.getString("phone_number")));
            ticket.setRoute(route);

            allTickets.add(ticket);

        }

        return allTickets;
    }
}
