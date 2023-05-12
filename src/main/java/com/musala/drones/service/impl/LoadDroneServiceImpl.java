package com.musala.drones.service.impl;

import com.musala.drones.constant.BatteryConstant;
import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.Drone;
import com.musala.drones.entities.LoadDroneRequest;
import com.musala.drones.entities.MedicationItem;
import com.musala.drones.entities.State;
import com.musala.drones.exception.NotAcceptableException;
import com.musala.drones.model.LoadDroneModel;
import com.musala.drones.model.ServerResponse;
import com.musala.drones.repo.DroneRepository;
import com.musala.drones.repo.LoadDroneRequestRepository;
import com.musala.drones.repo.MedicationItemRepository;
import com.musala.drones.repo.StateRepository;
import com.musala.drones.service.LoadDroneService;
import com.musala.drones.utility.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoadDroneServiceImpl implements LoadDroneService {

    private final DroneRepository droneRepository;

    private final StateRepository stateRepository;

    private final MedicationItemRepository medicationItemRepository;

    private final LoadDroneRequestRepository loadDroneRequestRepository;

    @Override
    public ServerResponse loadDrone(LoadDroneModel loadDroneModel) {
        Drone drone = droneRepository.getBySerialNumber(loadDroneModel.getDroneSerialNumber()).orElseThrow(() -> new NotAcceptableException("Drone of given serial number not available."));
        validateDrone(loadDroneModel, drone);
        updateDroneStatus(drone, StateConstant.LOADING);
        loadDrone(loadDroneModel, drone);
        updateDroneStatus(drone, StateConstant.LOADED);
        return ResponseUtility.getSuccessfulResponse("Drone successfully loaded with all the medication items.");
    }

    private void updateDroneStatus(Drone drone, StateConstant loading) {
        drone.setState(stateRepository.getStateByName(loading));
        droneRepository.save(drone);
    }

    private void loadDrone(LoadDroneModel loadDroneModel, Drone drone) {
        LoadDroneRequest droneLoadRequest = saveDroneLoadRequest(loadDroneModel, drone);
        saveMedicationItems(loadDroneModel, droneLoadRequest);
    }

    private void saveMedicationItems(LoadDroneModel loadDroneModel, LoadDroneRequest droneLoadRequest) {
        State loadedState = stateRepository.getStateByName(StateConstant.LOADED);
        loadDroneModel.getMedicationItems().forEach(item -> {
            MedicationItem medicationItem = new MedicationItem();
            medicationItem.setCode(item.getCode());
            medicationItem.setLoadDroneRequest(droneLoadRequest);
            medicationItem.setWeight(item.getWeight());
            medicationItem.setName(item.getName());
            medicationItem.setState(loadedState);
            medicationItemRepository.save(medicationItem);
        });
    }

    private LoadDroneRequest saveDroneLoadRequest(LoadDroneModel loadDroneModel, Drone drone) {
        LoadDroneRequest droneLoadRequest = new LoadDroneRequest();
        droneLoadRequest.setLoadTime(new Date());
        droneLoadRequest.setDrone(drone);
        droneLoadRequest.setLoadedItemCount(loadDroneModel.getMedicationItems().size());
        loadDroneRequestRepository.save(droneLoadRequest);
        return droneLoadRequest;
    }

    private void validateDrone(LoadDroneModel loadDroneModel, Drone drone) {
        if (!StateConstant.getLoadAllowedState().contains(drone.getState().getName())) {
            throw new NotAcceptableException("Drone is not available for loading.");
        }
        if (loadDroneModel.getTotalWeight() > drone.getModel().getCapacity()) {
            throw new NotAcceptableException("The weight of the loaded item exceeds the maximum capacity.");
        }
        if (drone.getBatteryCapacity() < BatteryConstant.MINIMUM_BATTERY_REQUIRED_TO_LOAD) {
            throw new NotAcceptableException("Drone does not have enough battery capacity to load.");
        }
    }
}
