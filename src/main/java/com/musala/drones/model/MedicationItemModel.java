package com.musala.drones.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedicationItemModel {

    @NotBlank(message = "Medication name is required.")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Only letters, numbers, hyphen and underscore are allowed")
    private String name;

    @NotNull(message = "Medication weight required in gram.")
    @Min(value = 1, message = "Medication weight should be minimum 1 gram.")
    private Double weight;

    @NotBlank(message = "Code is required.")
    @Pattern(regexp = "^[A-Z0-9_]*$")
    private String code;

    private String image;
}
