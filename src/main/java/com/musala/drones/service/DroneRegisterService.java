package com.musala.drones.service;

import com.musala.drones.model.DroneRegistration;
import com.musala.drones.model.ServerResponse;

public interface DroneRegisterService {

    ServerResponse register(DroneRegistration droneRegistration);
}
