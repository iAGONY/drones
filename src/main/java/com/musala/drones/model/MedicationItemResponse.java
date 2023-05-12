package com.musala.drones.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MedicationItemResponse {


    private String name;

    private Double weight;

    private String code;
}
