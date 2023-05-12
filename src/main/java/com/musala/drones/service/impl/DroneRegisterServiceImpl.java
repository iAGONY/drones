package com.musala.drones.service.impl;

import com.musala.drones.constant.ModelConstant;
import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.Drone;
import com.musala.drones.exception.DuplicateException;
import com.musala.drones.model.DroneRegistration;
import com.musala.drones.model.ServerResponse;
import com.musala.drones.repo.DroneRepository;
import com.musala.drones.repo.ModelRepository;
import com.musala.drones.repo.StateRepository;
import com.musala.drones.service.DroneRegisterService;
import com.musala.drones.utility.ResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DroneRegisterServiceImpl implements DroneRegisterService {

    private final DroneRepository droneRepository;

    private final ModelRepository modelRepository;

    private final StateRepository stateRepository;

    @Override
    public ServerResponse register(DroneRegistration droneRegistration) {
        checkIfSerialNumberAlreadyExist(droneRegistration);
        Drone drone = createNewDrone(droneRegistration);
        return ResponseUtility.getSuccessfulResponse("Drone was registered successfully.");
    }

    private void checkIfSerialNumberAlreadyExist(DroneRegistration droneRegistration) {
        boolean isExist = droneRepository.existsBySerialNumber(droneRegistration.getSerialNumber());
        if (isExist) {
            throw new DuplicateException("Provided serial number of drone already exist.");
        }
    }

    private Drone createNewDrone(DroneRegistration droneRegistration) {
        Drone drone = new Drone();
        drone.setState(stateRepository.getStateByName(StateConstant.IDLE));
        drone.setModel(modelRepository.getModelByName(ModelConstant.valueOf(droneRegistration.getModel())));
        drone.setSerialNumber(droneRegistration.getSerialNumber());
        drone.setBatteryCapacity(droneRegistration.getBatteryCapacity());
        return droneRepository.save(drone);
    }
}
