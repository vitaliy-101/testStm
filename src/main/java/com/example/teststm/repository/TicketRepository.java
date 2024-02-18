package com.example.teststm.repository;

import com.example.teststm.dtos.ticket.TicketFilterDtoIn;
import com.example.teststm.entities.Customer;
import com.example.teststm.entities.Route;
import com.example.teststm.entities.Ticket;
import com.example.teststm.mappers.customer.CustomerWithTicketMapper;
import com.example.teststm.mappers.route.RouteMapper;
import com.example.teststm.mappers.ticket.TicketMapper;
import com.example.teststm.mappers.ticket.TicketsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class TicketRepository {
    @Autowired
    private JdbcTemplate template;
    public TicketRepository(JdbcTemplate template){
        this.template = template;
    }
    public void save(Ticket ticket){
        template.update("INSERT INTO ticket (seat_number, price, date, time, route_id) VALUES(?, ?, ?, ?, ?)",
                ticket.getSeatNumber(), ticket.getPrice(), ticket.getDate(), ticket.getTime(), ticket.getRoute().getId());
    }


    public Boolean existById(Integer id){
        Ticket ticket = template.query("SELECT * FROM ticket WHERE id=?", new Object[]{id}, new TicketMapper()).stream().findAny().orElse(null);
        if (ticket == null){
            return false;
        }
        return true;
    }
    public void update(Ticket ticket, Integer id){
        template.update("UPDATE ticket SET seat_number = ?, price = ?," +
                        "date = ?, time = ?," +
                        "route_id = ? WHERE id = ?", ticket.getSeatNumber(), ticket.getPrice(),
                ticket.getDate(), ticket.getTime(), ticket.getRoute().getId(), id);
    }

    public void delete(Integer id){
        template.update("DELETE FROM ticket WHERE id = ?", id);
    }

    public Integer findCustomerId(Integer id){
        return template.queryForObject("SELECT customer_id FROM ticket WHERE id=?", new Object[]{id}, Integer.class);
    }
    public List<Ticket> findAll(TicketFilterDtoIn ticketFilterDtoIn){
        //должны выводится не купленные билеты!!!
        HashMap<String, Object> queryBody = getQueryBody(ticketFilterDtoIn);
        String sqlRequest = (String) queryBody.get("sqlRequest");
        Object[] params = (Object[]) queryBody.get("params");
        return template.query(sqlRequest, params, new TicketsMapper());
    }
    private HashMap<String, Object> getQueryBody(TicketFilterDtoIn ticketFilterDtoIn){
        String sql = "SELECT ticket.id AS ticket_id," +
                "seat_number, price, date, time, customer_id, route.id as route_id, starting_point, " +
                "destination_point, duration, route_transporter.transporter_id AS transp_id," +
                "name, phone_number FROM ticket " +
                "left join route on ticket.route_id = route.id " +
                "left join route_transporter on route.id = route_transporter.route_id " +
                "left join transporter on route_transporter.transporter_id = transporter.id ";
        List<Object> objects = new ArrayList<>();
        String where = "WHERE customer_id IS NULL AND";
        if (ticketFilterDtoIn.getStartingPoint() != null){
            where += " starting_point = ? AND";
            objects.add(ticketFilterDtoIn.getStartingPoint());
        }
        if (ticketFilterDtoIn.getDestinationPoint() != null){
            where += " destination_point = ? AND";
            objects.add(ticketFilterDtoIn.getDestinationPoint());
        }
        if (ticketFilterDtoIn.getTransporterName() != null){
            where += " name = ? AND";
            objects.add(ticketFilterDtoIn.getTransporterName());
        }
        if (ticketFilterDtoIn.getDate() != null){
            where += " date = ? AND";
            objects.add(ticketFilterDtoIn.getDate());
        }
        if (ticketFilterDtoIn.getTime() != null){
            where += " time = ? AND";
            objects.add(ticketFilterDtoIn.getTime());
        }
        if (!where.equals("WHERE")){
            where = where.substring(0, where.length() - 4);
            sql += where;
        }
        sql += " ORDER BY ticket_id "+
                "LIMIT " + ticketFilterDtoIn.getSize() + " OFFSET " +
                ticketFilterDtoIn.getSize() * (ticketFilterDtoIn.getPage() - 1);
        HashMap<String, Object> queryBody = new HashMap<>();
        queryBody.put("sqlRequest", sql);
        queryBody.put("params", objects.toArray());
        return queryBody;
    }



}
