package com.musala.drones.repo;

import com.musala.drones.entities.DroneEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneEventLogRepository extends JpaRepository<DroneEventLog, Long> {
}
