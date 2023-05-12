package com.musala.drones.constant;

import java.util.Arrays;
import java.util.List;

public enum StateConstant {

    IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;

    public static List<StateConstant> getLoadableState() {
        return Arrays.asList(IDLE, LOADING);
    }

    public static List<StateConstant> getNotLoadableState() {
        return Arrays.asList(LOADED, DELIVERING, DELIVERED, RETURNING);
    }
}
