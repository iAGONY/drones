package com.musala.drones.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroneBatteryResponse {

    private String serialNumber;
    private Integer batteryLevel;
}
