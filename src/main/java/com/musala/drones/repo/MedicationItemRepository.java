package com.musala.drones.repo;

import com.musala.drones.entities.MedicationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationItemRepository extends JpaRepository<MedicationItem, Long> {
}
