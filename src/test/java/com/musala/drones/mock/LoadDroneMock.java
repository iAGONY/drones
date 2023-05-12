package com.musala.drones.mock;

import com.musala.drones.model.LoadDroneModel;
import com.musala.drones.model.MedicationItemModel;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
public class LoadDroneMock {

    public static LoadDroneModel getLoadDrone(String serialNumber, Integer itemSize, Double weight) {
        LoadDroneModel loadDroneModel = new LoadDroneModel();
        loadDroneModel.setSerialNumber(serialNumber);
        loadDroneModel.setMedicationItems(getMedicationItems(itemSize, weight));
        return loadDroneModel;
    }

    public static List<MedicationItemModel> getMedicationItems(Integer itemSize, Double weight) {
        List<MedicationItemModel> medicationItemModels = new ArrayList<>();
        for (int size = itemSize; size <= itemSize; size++) {
            MedicationItemModel medicationItemModel = new MedicationItemModel();
            medicationItemModel.setName("Metformin");
            medicationItemModel.setWeight(weight);
            medicationItemModel.setImage(getImageAsByteString());
            medicationItemModels.add(medicationItemModel);
        }
        return medicationItemModels;
    }

    private static String getImageAsByteString() {
        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get("medication.jpg"));
            String base64String = Base64.getEncoder().encodeToString(imageBytes);
            return base64String;
        } catch (Exception e) {
            log.error("error converting image to byte string : {} ", e.getMessage());
            return "";
        }

    }

}
