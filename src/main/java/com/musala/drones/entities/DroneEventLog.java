package com.musala.drones.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "drone_event_log")
public class DroneEventLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "battery_capacity", nullable = false)
    private Integer batteryCapacity;

    @ManyToOne
    @JoinColumn(name = "drone", referencedColumnName = "id", nullable = false)
    private Drone drone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "recorded_date", nullable = false)
    private Date recordedDate;

}
