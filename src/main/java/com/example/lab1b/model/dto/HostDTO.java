package com.example.lab1b.model.dto;

import com.example.lab1b.model.Country;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class HostDTO {
    private String name;
    private String surname;
    private Country country;

    public HostDTO(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public HostDTO() {

    }
}
