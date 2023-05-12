package com.musala.drones.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoadableDroneResponse {

    private int totalLoadable;
    private List<DroneModelView> droneModelViews;
}
