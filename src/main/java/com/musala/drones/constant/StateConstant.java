package com.musala.drones.constant;

import java.util.Arrays;
import java.util.List;

public enum StateConstant {

    IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;

    public static List<StateConstant> getLoadAllowedState() {
        return Arrays.asList(IDLE);
    }

    public static List<StateConstant> getLoadNotAllowedState() {
        return Arrays.asList(LOADING, LOADED, DELIVERING, DELIVERED, RETURNING);
    }
}
