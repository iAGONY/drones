package com.musala.drones.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LoadDroneModel {

    @NotBlank(message = "Drone serial number is required.")
    private String droneSerialNumber;

    @NotNull(message = "Medication Items is required.")
    @Size(min = 1, message = "At-least one medication item is required.")
    private List<MedicationItemModel> medicationItems;

    public Double getTotalWeight() {
        return medicationItems.stream().map(MedicationItemModel::getWeight).reduce(0D, Double::sum);
    }
}
