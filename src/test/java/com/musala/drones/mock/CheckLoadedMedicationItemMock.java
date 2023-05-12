package com.musala.drones.mock;

import com.musala.drones.model.CheckLoadedMedicationItem;

public class CheckLoadedMedicationItemMock {

    public static CheckLoadedMedicationItem mock(String serialNumber) {
        CheckLoadedMedicationItem loadedMedicationItem = new CheckLoadedMedicationItem();
        loadedMedicationItem.setSerialNumber(serialNumber);
        return loadedMedicationItem;
    }
}
