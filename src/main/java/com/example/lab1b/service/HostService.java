package com.example.lab1b.service;

import com.example.lab1b.model.Host;
import com.example.lab1b.model.dto.HostDTO;

import java.util.List;
import java.util.Optional;

public interface HostService {

    List<Host> fetchAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(String name, String surname, Long countryId);
    Optional<Host> save(HostDTO hostDTO);
    Optional<Host> edit(Long id, String name, String surname, Long countryId);
    Optional<Host> deleteById(Long id);
}
