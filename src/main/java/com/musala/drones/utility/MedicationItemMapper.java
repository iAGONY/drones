package com.musala.drones.utility;

import com.musala.drones.entities.LoadDroneRequest;
import com.musala.drones.entities.MedicationItem;
import com.musala.drones.model.LoadedMedicationItemResponse;
import com.musala.drones.model.MedicationItemResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MedicationItemMapper {

    public static LoadedMedicationItemResponse mapMedicationItem(List<MedicationItem> medicationItems) {
        LoadedMedicationItemResponse medicationItemResponse = new LoadedMedicationItemResponse();
        medicationItemResponse.setMedicationItems(mapMedicationResponse(medicationItems));
        Optional<LoadDroneRequest> loadDroneRequest = medicationItems.stream().map(medicationItem -> medicationItem.getLoadDroneRequest()).findFirst();
        if (loadDroneRequest.isPresent()) {
            LoadDroneRequest request = loadDroneRequest.get();
            medicationItemResponse.setLoadedDate(request.getLoadTime());
            medicationItemResponse.setTotalItem(request.getLoadedItemCount());
        }
        return medicationItemResponse;
    }

    private static List<MedicationItemResponse> mapMedicationResponse(List<MedicationItem> medicationItems) {
        return medicationItems.stream().map(medicationItem -> {
            MedicationItemResponse itemResponse = new MedicationItemResponse();
            itemResponse.setWeight(medicationItem.getWeight());
            itemResponse.setName(medicationItem.getName());
            itemResponse.setCode(medicationItem.getCode());
            itemResponse.setImage(medicationItem.getImage());
            return itemResponse;
        }).collect(Collectors.toList());
    }
}
