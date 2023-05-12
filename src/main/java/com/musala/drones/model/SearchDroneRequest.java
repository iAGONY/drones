package com.musala.drones.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDroneRequest {

    @NotBlank(message = "Drone Serial Number is required.")
    private String serialNumber;
}
