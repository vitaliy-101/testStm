package com.example.teststm.dtos.route;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteBaseDto {
    @NotBlank(message = "startingPoint must not be empty")
    private String startingPoint;
    @NotBlank(message = "destinationPoint must not be empty")
    private String destinationPoint;
    @Min(value = 1, message = "the duration cannot be less than one minute")
    private Integer duration;
}
