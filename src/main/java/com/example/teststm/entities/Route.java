package com.example.teststm.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {
    private Integer id;
    private String startingPoint;
    private String destinationPoint;
    private Integer duration;
    private List<Ticket> tickets;
    private List<Transporter> transporters = new ArrayList<>();
    public Route (Integer id, String startingPoint, String destinationPoint, Integer duration){
        this.id = id;
        this.startingPoint = startingPoint;
        this.destinationPoint = destinationPoint;
        this.duration = duration;
    }
    public void addTransporter(Transporter transporter){
        transporters.add(transporter);
    }

}
