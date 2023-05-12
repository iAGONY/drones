package com.musala.drones.service.impl;

import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.Drone;
import com.musala.drones.exception.NotAcceptableException;
import com.musala.drones.mock.DroneMock;
import com.musala.drones.model.CheckLoadedMedicationItem;
import com.musala.drones.model.SearchDroneRequest;
import com.musala.drones.model.ServerResponse;
import com.musala.drones.repo.DroneRepository;
import com.musala.drones.repo.MedicationItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Optional;

import static com.musala.drones.mock.CheckLoadedMedicationItemMock.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneReportServiceImplTest {

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationItemRepository medicationItemRepository;

    @InjectMocks
    private DroneReportServiceImpl droneReportService;

    private CheckLoadedMedicationItem loadedMedicationItem;

    private String serialNumber = "DRONE_1";

    @Mock
    private Drone drone;

    @BeforeEach
    void before() {
        loadedMedicationItem = mock(serialNumber);
    }

    @Test
    void testWhenSerialNumberDroneNotFound() {
        when(droneRepository.getBySerialNumberAndState(serialNumber, StateConstant.LOADED)).thenReturn(Optional.empty());
        assertThrows(NotAcceptableException.class, () -> droneReportService.getLoadedItem(loadedMedicationItem));
    }

    @Test
    void testWhenSerialNumberDroneNotLoaded() {
        when(droneRepository.getBySerialNumberAndState(serialNumber, StateConstant.LOADED)).thenReturn(Optional.empty());
        assertThrows(NotAcceptableException.class, () -> droneReportService.getLoadedItem(loadedMedicationItem));
    }

    @Test
    void testWhenSuccessfulGetLoadedItem() {
        when(droneRepository.getBySerialNumberAndState(serialNumber, StateConstant.LOADED)).thenReturn(Optional.of(drone));
        when(medicationItemRepository.getByDroneId(drone.getId())).thenReturn(mock(ArrayList.class));
        ServerResponse serverResponse = droneReportService.getLoadedItem(loadedMedicationItem);
        assertEquals(HttpStatus.OK, serverResponse.getHttpStatus());
    }

    @Test
    void testGetAllLoadableDrone() {
        when(droneRepository.getAllLoadableDrone(StateConstant.getLoadableState())).thenReturn(mock(ArrayList.class));
        ServerResponse serverResponse = droneReportService.getAllLoadableDrone();
        assertEquals(HttpStatus.OK, serverResponse.getHttpStatus());
    }

    @Test
    void testGetBatteryLevelOfDrone() {
        SearchDroneRequest searchDroneRequest = new SearchDroneRequest();
        searchDroneRequest.setSerialNumber(serialNumber);

        when(droneRepository.getBySerialNumber(serialNumber)).thenReturn(Optional.of(DroneMock.getDrone(serialNumber)));
        ServerResponse serverResponse = droneReportService.getBatteryLevelOfDrone(searchDroneRequest);
        assertEquals(HttpStatus.OK, serverResponse.getHttpStatus());
    }


}