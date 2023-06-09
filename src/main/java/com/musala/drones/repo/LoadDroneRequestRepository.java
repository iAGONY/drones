package com.musala.drones.repo;

import com.musala.drones.entities.LoadDroneRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadDroneRequestRepository extends JpaRepository<LoadDroneRequest, Long> {

}
