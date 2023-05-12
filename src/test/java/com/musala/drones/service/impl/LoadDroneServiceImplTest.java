package com.musala.drones.service.impl;

import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Model;
import com.musala.drones.entities.State;
import com.musala.drones.exception.NotAcceptableException;
import com.musala.drones.mock.StateMock;
import com.musala.drones.model.LoadDroneModel;
import com.musala.drones.model.ServerResponse;
import com.musala.drones.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static com.musala.drones.mock.LoadDroneMock.getLoadDrone;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadDroneServiceImplTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private StateRepository stateRepository;

    @Mock
    private LoadDroneRequestRepository loadDroneRequestRepository;

    @Mock
    private MedicationItemRepository medicationItemRepository;

    @InjectMocks
    private LoadDroneServiceImpl droneLoadService;

    private LoadDroneModel loadDroneModel;

    @Mock
    private Drone drone;

    @Mock
    private Model model;

    private State idleState;
    private String serialNumber = "DRONE_1";


    @BeforeEach
    void before() {
        loadDroneModel = getLoadDrone(serialNumber, 2, 1000D);
        idleState = StateMock.getIdleState();
    }

    @Test
    void testWhenDroneNotAvailableOfGivenSerialNumber() {
        when(droneRepository.getBySerialNumber(serialNumber)).thenReturn(Optional.empty());
        assertThrows(NotAcceptableException.class, () -> droneLoadService.loadDrone(loadDroneModel));
    }

    @Test
    void testWhenDroneNotAvailableForLoading() {
        when(droneRepository.getBySerialNumber(serialNumber)).thenReturn(Optional.of(drone));
        when(drone.getState()).thenReturn(StateMock.getState(StateConstant.getNotLoadableState().stream().findFirst().get()));
        assertThrows(NotAcceptableException.class, () -> droneLoadService.loadDrone(loadDroneModel));
    }

    @Test
    void testWhenDroneIsLoadedGreaterWeightCapacity() {
        when(droneRepository.getBySerialNumber(serialNumber)).thenReturn(Optional.of(drone));
        when(drone.getState()).thenReturn(idleState);
        when(drone.getModel()).thenReturn(model);
        when(model.getCapacity()).thenReturn(100D);
        assertThrows(NotAcceptableException.class, () -> droneLoadService.loadDrone(loadDroneModel));
    }

    @Test
    void testWhenItemIsLoadedInDroneBelowBatteryCapacity() {
        loadDroneModel = getLoadDrone(serialNumber, 2, 10D);
        when(droneRepository.getBySerialNumber(serialNumber)).thenReturn(Optional.of(drone));
        when(drone.getState()).thenReturn(idleState);
        when(drone.getModel()).thenReturn(model);
        when(model.getCapacity()).thenReturn(500D);
        when(drone.getBatteryCapacity()).thenReturn(20);
        assertThrows(NotAcceptableException.class, () -> droneLoadService.loadDrone(loadDroneModel));
    }

    @Test
    void testWhenDroneIsLoadedSuccessfully() {
        loadDroneModel = getLoadDrone(serialNumber, 2, 10D);
        when(droneRepository.getBySerialNumber(serialNumber)).thenReturn(Optional.of(drone));
        when(drone.getState()).thenReturn(idleState);
        when(drone.getModel()).thenReturn(model);
        when(model.getCapacity()).thenReturn(500D);
        when(drone.getBatteryCapacity()).thenReturn(100);
        ServerResponse serverResponse = droneLoadService.loadDrone(loadDroneModel);
        assertEquals(HttpStatus.OK, serverResponse.getHttpStatus());
    }
}