package com.musala.drones.mock;

import com.musala.drones.constant.ModelConstant;
import com.musala.drones.entities.Model;

public class ModelMock {

    public static Model getModel() {
        Model model = new Model();
        model.setName(ModelConstant.LIGHT_WEIGHT);
        return model;
    }
}
