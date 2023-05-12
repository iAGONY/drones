package com.musala.drones.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "load_drone_request")
public class LoadDroneRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "drone", referencedColumnName = "id", nullable = false)
    private Drone drone;

    @Column(name = "load_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date loadTime;

    @Column(name = "loaded_item_count", nullable = false)
    private Integer loadedItemCount;
}
