package com.musala.drones.service;

import com.musala.drones.model.LoadDroneModel;
import com.musala.drones.model.ServerResponse;

public interface LoadDroneService {

    ServerResponse loadDrone(LoadDroneModel loadDroneModel);
}
