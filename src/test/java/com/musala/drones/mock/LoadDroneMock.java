package com.musala.drones.mock;

import com.musala.drones.model.LoadDroneModel;
import com.musala.drones.model.MedicationItemModel;

import java.util.ArrayList;
import java.util.List;

public class LoadDroneMock {

    public static LoadDroneModel getLoadDrone(String serialNumber, Integer itemSize, Double weight) {
        LoadDroneModel loadDroneModel = new LoadDroneModel();
        loadDroneModel.setDroneSerialNumber(serialNumber);
        loadDroneModel.setMedicationItems(getMedicationItems(itemSize, weight));
        return loadDroneModel;
    }

    public static List<MedicationItemModel> getMedicationItems(Integer itemSize, Double weight) {
        List<MedicationItemModel> medicationItemModels = new ArrayList<>();
        for (int size = itemSize; size <= itemSize; size++) {
            MedicationItemModel medicationItemModel = new MedicationItemModel();
            medicationItemModel.setName("Metformin");
            medicationItemModel.setWeight(weight);
            medicationItemModels.add(medicationItemModel);
        }
        return medicationItemModels;
    }

}
