package com.musala.drones.mock;

import com.musala.drones.model.DroneRegistration;

public class DroneRegistrationMock {

    public static DroneRegistration getDroneRegistration(String serialNumber) {
        DroneRegistration droneRegistration = new DroneRegistration();
        droneRegistration.setSerialNumber(serialNumber);
        droneRegistration.setModel("LIGHT_WEIGHT");
        droneRegistration.setBatteryCapacity(100);
        return droneRegistration;
    }
}
