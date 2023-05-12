package com.musala.drones.repo;

import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    boolean existsBySerialNumber(String serialNumber);
    Optional<Drone> getBySerialNumber(String serialNumber);

    @Query("SELECT d from Drone d where d.serialNumber =:serialNumber and d.state.name= :stateName")
    Optional<Drone> getBySerialNumberAndState(String serialNumber, StateConstant stateName);

    @Query("SELECT d from Drone d where d.state.name in (:stateConstants)")
    List<Drone> getAllLoadableDrone(List<StateConstant> stateConstants);
}
