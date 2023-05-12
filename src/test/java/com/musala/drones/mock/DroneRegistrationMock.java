package com.musala.drones.mock;

import com.musala.drones.constant.ModelConstant;
import com.musala.drones.model.DroneRegistration;

public class DroneRegistrationMock {

    public static DroneRegistration getDroneRegistration(String serialNumber) {
        DroneRegistration droneRegistration = new DroneRegistration();
        droneRegistration.setSerialNumber(serialNumber);
        droneRegistration.setModel(ModelConstant.MIDDLE_WEIGHT.name());
        droneRegistration.setBatteryCapacity(100);
        return droneRegistration;
    }
}
