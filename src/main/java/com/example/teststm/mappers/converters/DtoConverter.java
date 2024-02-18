package com.example.teststm.mappers.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {
    @Autowired
    private ModelMapper modelMapper;
    public <T> T simpleConvert(Object obj, Class<T> clazz) {
        return modelMapper.map(obj, clazz);
    }

    public <T> List<T> simpleConvert(List<?> entitiesList, Class<T> clazz) {
        return entitiesList.stream().map(x -> simpleConvert(x, clazz)).collect(Collectors.toList());
    }

}
