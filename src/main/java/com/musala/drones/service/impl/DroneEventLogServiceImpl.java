package com.musala.drones.service.impl;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.DroneEventLog;
import com.musala.drones.repo.DroneEventLogRepository;
import com.musala.drones.service.DroneEventLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneEventLogServiceImpl implements DroneEventLogService {

    private final DroneEventLogRepository droneEventLogRepository;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveDroneEventLog(Drone drone) {
        DroneEventLog droneEventLog = new DroneEventLog();
        droneEventLog.setDrone(drone);
        droneEventLog.setBatteryCapacity(drone.getBatteryCapacity());
        droneEventLog.setRecordedDate(new Date());
        droneEventLogRepository.save(droneEventLog);
        log.debug("::: Saved DroneEventLog ::: ");
    }
}
