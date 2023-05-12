package com.musala.drones.repo;

import com.musala.drones.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    boolean existsBySerialNumber(String serialNumber);
    Optional<Drone> getBySerialNumber(String serialNumber);
}
