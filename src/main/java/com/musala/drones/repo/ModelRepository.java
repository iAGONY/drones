package com.musala.drones.repo;

import com.musala.drones.constant.ModelConstant;
import com.musala.drones.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Model getModelByName(ModelConstant modelConstant);

}
