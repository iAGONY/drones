package com.musala.drones.utility;


import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Model;
import com.musala.drones.entities.State;
import com.musala.drones.model.DroneModelView;
import com.musala.drones.model.LoadableDroneResponse;
import com.musala.drones.model.ModelDto;
import com.musala.drones.model.StateDto;

import java.util.List;
import java.util.stream.Collectors;

public class LoadableDroneResponseMapper {

    public static LoadableDroneResponse map(List<Drone> drones) {
        LoadableDroneResponse response = new LoadableDroneResponse();
        response.setTotalLoadable(drones.size());
        response.setDroneModelViews(mapDroneModelView(drones));
        return response;
    }

    private static List<DroneModelView> mapDroneModelView(List<Drone> drones) {
        return drones.stream().map(drone -> {
            DroneModelView model = new DroneModelView();
            model.setModel(getModelDto(drone.getModel()));
            model.setState(getStateDto(drone.getState()));
            model.setSerialNumber(drone.getSerialNumber());
            model.setRegistrationDate(drone.getRegistrationDate());
            return model;
        }).collect(Collectors.toList());
    }

    private static StateDto getStateDto(State state) {
        StateDto dto = new StateDto();
        dto.setName(state.getName().name());
        return dto;
    }

    private static ModelDto getModelDto(Model model) {
        ModelDto dto = new ModelDto();
        dto.setName(model.getName().name());
        dto.setDescription(model.getDescription());
        dto.setCapacity(model.getCapacity());
        return dto;
    }
}
