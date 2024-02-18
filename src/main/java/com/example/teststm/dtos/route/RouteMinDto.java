package com.example.teststm.dtos.route;

import com.example.teststm.dtos.transporter.TransporterMinDto;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteMinDto extends RouteBaseDto{
    private Integer id;
    private List<TransporterMinDto> transporters;
}
