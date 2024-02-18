package com.example.teststm.mappers.transporter;

import com.example.teststm.entities.Ticket;
import com.example.teststm.entities.Transporter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransporterMapper implements RowMapper<Transporter> {
    @Override
    public Transporter mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Transporter(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("phone_number"));
    }
}