package com.musala.drones.repo;

import com.musala.drones.constant.ModelConstant;
import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    State getStateByName(StateConstant stateConstant);
}
