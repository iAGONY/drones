package com.musala.drones.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckLoadedMedicationItem {

    @NotBlank(message = "Drone Serial Number is required.")
    private String serialNumber;
}
