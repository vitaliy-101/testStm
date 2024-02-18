package com.example.teststm.services;

import com.example.teststm.entities.Ticket;
import com.example.teststm.entities.Transporter;
import com.example.teststm.exceptions.NotFoundByIdException;
import com.example.teststm.repository.TransporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransporterService {
    @Autowired
    private TransporterRepository repository;
    public void createTransporter(Transporter transporter){
        repository.save(transporter);
    }
    public void updateTransporter(Transporter transporter, Integer id) throws NotFoundByIdException {
        if (!repository.existById(id)){
            throw new NotFoundByIdException(Transporter.class, id);
        }
        repository.update(transporter, id);
    }
    public void deleteTransporter(Integer id) throws NotFoundByIdException {
        if (!repository.existById(id)){
            throw new NotFoundByIdException(Transporter.class, id);
        }
        repository.delete(id);
    }
}
