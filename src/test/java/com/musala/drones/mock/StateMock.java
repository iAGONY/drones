package com.musala.drones.mock;

import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.State;

public class StateMock {

    public static State getIdleState() {
        State state = new State();
        state.setName(StateConstant.IDLE);
        return state;
    }

    public static State getState(StateConstant stateConstant) {
        State state = new State();
        state.setName(stateConstant);
        return state;
    }
}
