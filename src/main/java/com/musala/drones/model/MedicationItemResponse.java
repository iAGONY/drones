package com.musala.drones.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MedicationItemResponse {

    private String name;

    private Double weight;

    private String code;

    private byte[] image;
}
