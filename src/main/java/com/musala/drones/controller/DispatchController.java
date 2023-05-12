package com.musala.drones.controller;


import com.musala.drones.model.DroneRegistration;
import com.musala.drones.model.LoadDroneModel;
import com.musala.drones.model.ServerResponse;
import com.musala.drones.service.LoadDroneService;
import com.musala.drones.service.DroneRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.musala.drones.constant.ApiConstant.*;

@RestController
@RequestMapping(path = DISPATCH + "/" + DRONE)
@RequiredArgsConstructor
public class DispatchController {

    private final DroneRegisterService droneRegisterService;

    private final LoadDroneService loadDroneService;

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

//    @PostMapping(path = CHECK + "/" + LOADED + "/" + MEDICATION)
//    public ResponseEntity<?> checkLoadedMedication(@RequestBody @Valid ThirdPartyLoanDetailsRequestModel thirdPartyLoanDetailsRequestModel) {
//        return new ResponseEntity(serverResponse, serverResponse.getHttpStatus());
//    }
//
//    @PostMapping(path = CHECK + "/" + AVAILABLE + "/" + DRONES)
//    public ResponseEntity<?> checkAvailableDrones(@RequestBody @Valid ThirdPartyLoanDetailsRequestModel thirdPartyLoanDetailsRequestModel) {
//        return new ResponseEntity(serverResponse, serverResponse.getHttpStatus());
//    }
//
//    @PostMapping(path = CHECK + "/" + BATTERY_LEVEL)
//    public ResponseEntity<?> checkBatteryLevel(@RequestBody @Valid ThirdPartyLoanDetailsRequestModel thirdPartyLoanDetailsRequestModel) {
//        return new ResponseEntity(serverResponse, serverResponse.getHttpStatus());
//    }
}
