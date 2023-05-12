package com.musala.drones.repo;

import com.musala.drones.entities.MedicationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationItemRepository extends JpaRepository<MedicationItem, Long> {

    @Query("SELECT mi from MedicationItem mi WHERE mi.loadDroneRequest.drone.id= :droneId")
    List<MedicationItem> getByDroneId(Long droneId);
}
