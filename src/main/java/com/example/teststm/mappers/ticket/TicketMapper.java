package com.example.teststm.mappers.ticket;

import com.example.teststm.entities.Route;
import com.example.teststm.entities.Ticket;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TicketMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ticket(rs.getInt("id"),
                rs.getInt("seat_number"),
                rs.getDate("date").toLocalDate(),
                rs.getString("time"),
                rs.getInt("price"));
    }
}
