package com.musala.drones.controller;


import com.musala.drones.model.*;
import com.musala.drones.service.DroneRegisterService;
import com.musala.drones.service.DroneReportService;
import com.musala.drones.service.LoadDroneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.musala.drones.constant.ApiConstant.*;

@RestController
@RequestMapping(path = DISPATCH + "/" + DRONE)
@RequiredArgsConstructor
public class DispatchController {

    private final DroneRegisterService droneRegisterService;

    private final LoadDroneService loadDroneService;

    private final DroneReportService droneReportService;

    @PostMapping(path = REGISTER)
    public ResponseEntity<?> register(@RequestBody @Valid DroneRegistration droneRegistration) {
        ServerResponse serverResponse = droneRegisterService.register(droneRegistration);
        return new ResponseEntity(serverResponse, serverResponse.getHttpStatus());
    }

    @PostMapping(path = LOAD)
    public ResponseEntity<?> load(@RequestBody @Valid LoadDroneModel loadDroneModel) {
        ServerResponse serverResponse = loadDroneService.loadDrone(loadDroneModel);
        return new ResponseEntity(serverResponse, serverResponse.getHttpStatus());
    }

    @PostMapping(path = CHECK + "/" + LOADED + "/" + MEDICATION)
    public ResponseEntity<?> checkLoadedMedication(@RequestBody @Valid CheckLoadedMedicationItem checkLoadedMedicationItem) {
        ServerResponse serverResponse = droneReportService.getLoadedItem(checkLoadedMedicationItem);
        return new ResponseEntity(serverResponse, serverResponse.getHttpStatus());
    }

    @GetMapping(path = CHECK + "/" + AVAILABLE)
    public ResponseEntity<?> checkAvailableDrones() {
        ServerResponse serverResponse = droneReportService.getAllLoadableDrone();
        return new ResponseEntity(serverResponse, serverResponse.getHttpStatus());
    }

    @PostMapping(path = CHECK + "/" + BATTERY_LEVEL)
    public ResponseEntity<?> checkBatteryLevel(@RequestBody @Valid SearchDroneRequest searchDroneRequest) {
        ServerResponse serverResponse = droneReportService.getBatteryLevelOfDrone(searchDroneRequest);
        return new ResponseEntity(serverResponse, serverResponse.getHttpStatus());
    }
}
