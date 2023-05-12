package com.musala.drones.mock;

import com.musala.drones.entities.Drone;

public class DroneMock {

    public static Drone getDrone(String serialNumber) {
        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);
        return drone;
    }
}
