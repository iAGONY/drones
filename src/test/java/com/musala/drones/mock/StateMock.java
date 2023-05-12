package com.musala.drones.mock;

import com.musala.drones.constant.StateConstant;
import com.musala.drones.entities.State;

public class StateMock {

    public static State getState() {
        State state = new State();
        state.setName(StateConstant.IDLE);
        return state;
    }
}
