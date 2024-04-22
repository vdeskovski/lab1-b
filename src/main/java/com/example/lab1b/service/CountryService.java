package com.example.lab1b.service;

import com.example.lab1b.model.Country;
import com.example.lab1b.model.dto.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(String name, String continent);
    Optional<Country> save(CountryDTO countryDTO);
    Optional<Country> edit(Long id, String name, String continent);
    Optional<Country> deleteById(Long id);

}
