package com.musala.drones.constant;

public enum ModelConstant {

    LIGHT_WEIGHT, MIDDLE_WEIGHT, CRUISER_WEIGHT, HEAVY_WEIGHT;

    public static boolean isValidModel(String model) {
        try {
            ModelConstant.valueOf(model);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
