package com.musala.drones.model;

import com.musala.drones.constant.ModelConstant;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class DroneRegistration implements Serializable {

    @NotBlank(message = "Serial number is required.")
    @Size(max = 100, message = "Max supported serial number characters is 100")
    private String serialNumber;

    @NotBlank(message = "Model is required.")
    private String model;

    @NotNull(message = "Battery capacity is required.")
    @Max(value = 100, message = "Battery capacity must be between 0 to 100%.")
    @Min(value = 0, message = "Battery capacity must be between 0 to 100%.")
    private Integer batteryCapacity;

    @AssertTrue(message = "Invalid model. Please go through the API doc provided.")
    public boolean isModel() {
        return ModelConstant.isValidModel(this.model);
    }
}
