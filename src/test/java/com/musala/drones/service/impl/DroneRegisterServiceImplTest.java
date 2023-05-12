package com.musala.drones.service.impl;

import com.musala.drones.constant.ModelConstant;
import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.Model;
import com.musala.drones.entities.State;
import com.musala.drones.exception.DuplicateException;
import com.musala.drones.mock.StateMock;
import com.musala.drones.model.DroneRegistration;
import com.musala.drones.model.ServerResponse;
import com.musala.drones.repo.DroneRepository;
import com.musala.drones.repo.ModelRepository;
import com.musala.drones.repo.StateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.musala.drones.mock.DroneRegistrationMock.getDroneRegistration;
import static com.musala.drones.mock.ModelMock.getModel;
import static com.musala.drones.mock.StateMock.getIdleState;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneRegisterServiceImplTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private DroneRegisterServiceImpl droneRegisterService;

    private DroneRegistration droneRegistration;

    private String serialNumber = "DRONE_1";

    @BeforeEach
    void before() {
        droneRegistration = getDroneRegistration(serialNumber);
    }

    @Test
    void testDuplicateException() {
        when(droneRepository.existsBySerialNumber(serialNumber)).thenReturn(true);
        assertThrows(DuplicateException.class, () -> droneRegisterService.register(droneRegistration));
    }

    @Test
    public void testRegisterSuccessfulResponse() {
        Model model = getModel();
        State state = getIdleState();
        when(modelRepository.getModelByName(any())).thenReturn(model);
        when(stateRepository.getStateByName(StateConstant.IDLE)).thenReturn(state);
        when(droneRepository.existsBySerialNumber(serialNumber)).thenReturn(false);
        ServerResponse serverResponse = droneRegisterService.register(droneRegistration);
        assertEquals(HttpStatus.OK, serverResponse.getHttpStatus());
    }


}