package com.musala.drones.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "drone")
public class Drone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "model", referencedColumnName = "id", nullable = false)
    private Model model;

    @Column(name = "battery_capacity", nullable = false)
    private Integer batteryCapacity;

    @ManyToOne
    @JoinColumn(name = "state", referencedColumnName = "id", nullable = false)
    private State state;
}
