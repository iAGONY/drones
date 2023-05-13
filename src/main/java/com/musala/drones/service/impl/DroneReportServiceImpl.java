package com.musala.drones.service.impl;

import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.Drone;
import com.musala.drones.entities.MedicationItem;
import com.musala.drones.exception.NotAcceptableException;
import com.musala.drones.model.CheckLoadedMedicationItem;
import com.musala.drones.model.SearchDroneRequest;
import com.musala.drones.model.ServerResponse;
import com.musala.drones.repo.DroneRepository;
import com.musala.drones.repo.MedicationItemRepository;
import com.musala.drones.service.DroneReportService;
import com.musala.drones.utility.DroneBatteryLevelResponseMapper;
import com.musala.drones.utility.LoadableDroneResponseMapper;
import com.musala.drones.utility.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musala.drones.utility.MedicationItemMapper.mapMedicationItem;

@Service
@RequiredArgsConstructor
public class DroneReportServiceImpl implements DroneReportService {
    private final DroneRepository droneRepository;
    private final MedicationItemRepository medicationItemRepository;

    @Override
    public ServerResponse getLoadedItem(CheckLoadedMedicationItem checkLoadedMedicationItem) {
        Drone drone = droneRepository.getBySerialNumberAndState(checkLoadedMedicationItem.getSerialNumber(), StateConstant.LOADED)
                .orElseThrow(() -> new NotAcceptableException("Drone with given serial number not available or not in loaded state."));
        List<MedicationItem> medicationItems = medicationItemRepository.getByDroneId(drone.getId());
        return ResponseUtility.getSuccessfulResponse(mapMedicationItem(medicationItems), "Successfully fetched loaded medication items");
    }

    @Override
    public ServerResponse getAllLoadableDrone() {
        List<Drone> drones = droneRepository.getAllLoadableDrone(StateConstant.getLoadableState());
        return ResponseUtility.getSuccessfulResponse(LoadableDroneResponseMapper.map(drones), "Successfully fetched loadable drones.");
    }

    @Override
    public ServerResponse getBatteryLevelOfDrone(SearchDroneRequest searchDroneRequest) {
        Drone drone = droneRepository.getBySerialNumber(searchDroneRequest.getSerialNumber())
                .orElseThrow(() -> new NotAcceptableException("Drone with given serial number not registered."));
        return ResponseUtility.getSuccessfulResponse(DroneBatteryLevelResponseMapper.map(drone), "Successfully fetched drone battery level.");
    }

}
