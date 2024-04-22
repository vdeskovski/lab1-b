package com.example.lab1b.service.impl;

import com.example.lab1b.model.Country;
import com.example.lab1b.model.dto.CountryDTO;
import com.example.lab1b.model.exceptions.CountryAlreadyExistsException;
import com.example.lab1b.model.exceptions.InvalidCountryIdException;
import com.example.lab1b.repo.CountryRepo;
import com.example.lab1b.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepo countryRepo;

    public CountryServiceImpl(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepo.findById(id);
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name,continent);
        if (findAll().stream().anyMatch(i -> i.getName().equals(name))){
            throw new CountryAlreadyExistsException(String.format("%s already exists", name));
        }
        return Optional.of(countryRepo.save(country));
    }

    @Override
    public Optional<Country> save(CountryDTO countryDTO) {
        if (findAll().stream().anyMatch(i -> i.getName().equals(countryDTO.getName()))){
            throw new CountryAlreadyExistsException(String.format("%s already exists", countryDTO.getName()));
        }
        Country country = new Country(countryDTO.getName(), countryDTO.getContinent());
        countryRepo.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = countryRepo.findById(id).orElseThrow(InvalidCountryIdException::new);
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(countryRepo.save(country));
    }
    @Override
    public Optional<Country> deleteById(Long id) {
        Country country = countryRepo.findById(id).orElseThrow(InvalidCountryIdException::new);
        countryRepo.delete(country);
        return Optional.of(country);
    }
}
