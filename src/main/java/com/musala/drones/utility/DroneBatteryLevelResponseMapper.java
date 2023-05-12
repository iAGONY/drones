package com.musala.drones.utility;

import com.musala.drones.entities.Drone;
import com.musala.drones.model.DroneBatteryResponse;

public class DroneBatteryLevelResponseMapper {

    public static DroneBatteryResponse map(Drone drone) {
        DroneBatteryResponse response = new DroneBatteryResponse();
        response.setBatteryLevel(drone.getBatteryCapacity());
        response.setSerialNumber(drone.getSerialNumber());
        return response;
    }
}
