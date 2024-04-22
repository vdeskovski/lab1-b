package com.example.lab1b.service.impl;

import com.example.lab1b.model.Country;
import com.example.lab1b.model.Host;
import com.example.lab1b.model.dto.HostDTO;
import com.example.lab1b.model.exceptions.InvalidCountryIdException;
import com.example.lab1b.model.exceptions.InvalidHostIdException;
import com.example.lab1b.repo.CountryRepo;
import com.example.lab1b.repo.HostRepo;
import com.example.lab1b.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepo hostRepo;
    private final CountryRepo countryRepo;

    public HostServiceImpl(HostRepo hostRepo, CountryRepo countryRepo) {
        this.hostRepo = hostRepo;
        this.countryRepo = countryRepo;
    }

    @Override
    public List<Host> fetchAll() {
        return hostRepo.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepo.findById(id);
    }

    @Override
    public Optional<Host> save(String name, String surname, Long countryId) {
        Country country = countryRepo.findById(countryId).orElseThrow(InvalidCountryIdException::new);
        Host host = new Host(name,surname,country);
        return Optional.of(hostRepo.save(host));
    }

    @Override
    public Optional<Host> save(HostDTO hostDTO) {
        Host host = new Host(hostDTO.getName(), hostDTO.getSurname(), hostDTO.getCountry());
        return Optional.of(hostRepo.save(host));
    }

    @Override
    public Optional<Host> edit(Long id, String name, String surname, Long countryId) {
        Country country = countryRepo.findById(countryId).orElseThrow(InvalidCountryIdException::new);
        Host host = findById(id).orElseThrow(InvalidHostIdException::new);
        host.setName(name);
        host.setSurname(surname);
        host.setCountry(country);
        return Optional.of(hostRepo.save(host));
    }

    @Override
    public Optional<Host> deleteById(Long id) {
        Host host = findById(id).orElseThrow(InvalidHostIdException::new);
        hostRepo.delete(host);
        return Optional.of(host);
    }
}
