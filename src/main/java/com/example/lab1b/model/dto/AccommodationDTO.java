package com.example.lab1b.model.dto;

import com.example.lab1b.model.Category;
import com.example.lab1b.model.Host;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AccommodationDTO {
    private String name;
    private Category category;
    private Host host;
    private Integer numRooms;
    private boolean rented;

    public AccommodationDTO(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        rented = false;
    }

    public AccommodationDTO() {
        rented = false;
    }
}
