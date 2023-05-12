package com.musala.drones.service.impl;

import com.musala.drones.entities.Drone;
import com.musala.drones.repo.DroneRepository;
import com.musala.drones.service.DroneEventLogService;
import com.musala.drones.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final DroneRepository droneRepository;

    private final DroneEventLogService droneEventLogService;

    @Override
    @Transactional
    public void updateSchedule() {
        List<Drone> drones = droneRepository.findAll();
        drones.forEach(drone -> {
            try {
                droneEventLogService.saveDroneEventLog(drone);
            } catch (Exception e) {
                log.error(" ERROR WHILE UPDATING LOG OF DRONE SERIAL NUMBER : {} ", drone.getSerialNumber());
            }
        });
    }
}
