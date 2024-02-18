package com.example.teststm.repository;

import com.example.teststm.entities.Route;
import com.example.teststm.entities.Ticket;
import com.example.teststm.entities.Transporter;
import com.example.teststm.mappers.ticket.TicketMapper;
import com.example.teststm.mappers.transporter.TransporterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransporterRepository {
    @Autowired
    private JdbcTemplate template;
    public TransporterRepository(JdbcTemplate template){
        this.template = template;
    }
    public void save(Transporter transporter){
        template.update("INSERT INTO transporter (name, phone_number) VALUES(?, ?)", transporter.getName(),
                transporter.getPhoneNumber());
        //TODO
        Integer transporterId = template.queryForObject("SELECT id FROM transporter ORDER BY transporter.id DESC LIMIT 1", Integer.class);
        for (Route route : transporter.getRoutes()){
            template.update("INSERT INTO route_transporter (transporter_id, route_id) VALUES (?, ?)", transporterId,
                    route.getId());
        }
    }
    public Boolean existById(Integer id){
        Transporter transporter = template.query("SELECT * FROM transporter WHERE id=?", new Object[]{id}, new TransporterMapper()).stream().findAny().orElse(null);
        if (transporter == null){
            return false;
        }
        return true;
    }
    public void update(Transporter transporter, Integer id){
        template.update("UPDATE transporter SET name = ?, phone_number = ?" +
                        " WHERE id = ?", transporter.getName(), transporter.getPhoneNumber(), id);
        template.update("DELETE FROM route_transporter WHERE transporter_id = ?", id);
        for (Route route : transporter.getRoutes()){
            template.update("INSERT INTO route_transporter (transporter_id, route_id) VALUES (?, ?)", id,
                    route.getId());
        }
    }
    public void delete(Integer id){
        template.update("DELETE FROM transporter WHERE id = ?", id);
        template.update("DELETE FROM route_transporter WHERE transporter_id = ?", id);
    }
}
