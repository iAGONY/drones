package com.musala.drones.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "medication_item")
public class MedicationItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "load_drone_request", referencedColumnName = "id", nullable = false)
    private LoadDroneRequest loadDroneRequest;

    @Column(name = "image")
    @Lob
    private byte[] image;
}
