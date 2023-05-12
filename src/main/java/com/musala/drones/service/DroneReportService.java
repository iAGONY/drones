package com.musala.drones.service;

import com.musala.drones.model.CheckLoadedMedicationItem;
import com.musala.drones.model.SearchDroneRequest;
import com.musala.drones.model.ServerResponse;

public interface DroneReportService {

    ServerResponse getLoadedItem(CheckLoadedMedicationItem checkLoadedMedicationItem);
    ServerResponse getAllLoadableDrone();

    ServerResponse getBatteryLevelOfDrone(SearchDroneRequest searchDroneRequest);

}