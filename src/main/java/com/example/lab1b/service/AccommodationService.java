package com.example.lab1b.service;

import com.example.lab1b.model.Accommodation;
import com.example.lab1b.model.Category;
import com.example.lab1b.model.dto.AccommodationDTO;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    List<Accommodation> findAllRented();
    List<Accommodation> findAllUnRented();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(String name, Category category, Long hostId, Integer numRooms);
    Optional<Accommodation> save(AccommodationDTO accommodationDTO);
    Optional<Accommodation> edit(Long id, String name, Category category, Long hostId, Integer numRooms);
    Optional<Accommodation> edit(Long id, AccommodationDTO accommodationDTO);
    Optional<Accommodation> deleteById(Long id);

    Optional<Accommodation> rent(Long id);

}
