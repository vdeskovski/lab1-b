package com.example.lab1b.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Accommodation {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private Integer numRooms;
    private boolean rented;

    public Accommodation(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        rented = false;
    }

    public Accommodation() {
        rented = false;
    }
}
