package com.example.lab1b.service.impl;

import com.example.lab1b.model.Accommodation;
import com.example.lab1b.model.Category;
import com.example.lab1b.model.Host;
import com.example.lab1b.model.dto.AccommodationDTO;
import com.example.lab1b.model.exceptions.AccommodationFullException;
import com.example.lab1b.model.exceptions.InvalidAccommodationIdException;
import com.example.lab1b.model.exceptions.InvalidHostIdException;
import com.example.lab1b.repo.AccommodationRepo;
import com.example.lab1b.repo.HostRepo;
import com.example.lab1b.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final HostRepo hostRepo;
    private final AccommodationRepo accommodationRepo;

    public AccommodationServiceImpl(HostRepo hostRepo, AccommodationRepo accommodationRepo) {
        this.hostRepo = hostRepo;
        this.accommodationRepo = accommodationRepo;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepo.findAll();
    }

    @Override
    public List<Accommodation> findAllRented() {
        return accommodationRepo.findAllByRentedIsTrue();
    }

    @Override
    public List<Accommodation> findAllUnRented() {
        return accommodationRepo.findAllByRentedIsFalse();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        Accommodation accommodation = accommodationRepo.findById(id).orElseThrow(InvalidAccommodationIdException::new);
        return Optional.ofNullable(accommodation);
    }

    @Override
    public Optional<Accommodation> save(String name, Category category, Long hostId, Integer numRooms) {
        Host host = hostRepo.findById(hostId).orElseThrow(InvalidHostIdException::new);
        Accommodation accommodation = new Accommodation(name, category, host, numRooms);
        return Optional.of(accommodationRepo.save(accommodation));
    }

    @Override
    public Optional<Accommodation> save(AccommodationDTO accommodationDTO) {
        if (hostRepo.findAll().stream().noneMatch(i->i.getId().equals(accommodationDTO.getHost().getId()))){
            throw new InvalidHostIdException();
        }
        Accommodation accommodation = new Accommodation(accommodationDTO.getName(),
                accommodationDTO.getCategory(),
                accommodationDTO.getHost(), accommodationDTO.getNumRooms());
        return Optional.of(accommodationRepo.save(accommodation));
    }

    @Override
    public Optional<Accommodation> edit(Long id, String name, Category category, Long hostId, Integer numRooms) {
        Host host = hostRepo.findById(hostId).orElseThrow(InvalidHostIdException::new);
        Accommodation accommodation = accommodationRepo.findById(id).orElseThrow(InvalidAccommodationIdException::new);
        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setHost(host);
        accommodation.setNumRooms(numRooms);
        return Optional.of(accommodationRepo.save(accommodation));
    }

    @Override
    public Optional<Accommodation> edit(Long id, AccommodationDTO accommodationDTO) {
        Accommodation accommodation = accommodationRepo.findById(id).orElseThrow(InvalidAccommodationIdException::new);
        accommodation.setName(accommodationDTO.getName());
        accommodation.setCategory(accommodationDTO.getCategory());
        accommodation.setHost(accommodationDTO.getHost());
        accommodation.setNumRooms(accommodationDTO.getNumRooms());
        return Optional.of(accommodationRepo.save(accommodation));
    }

    @Override
    public Optional<Accommodation> deleteById(Long id) {
        Accommodation accommodation = findById(id).orElseThrow(InvalidAccommodationIdException::new);
        accommodationRepo.delete(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> rent(Long id) {
        Accommodation accommodation = accommodationRepo.findById(id).orElseThrow(InvalidAccommodationIdException::new);
        accommodation.setRented(!accommodation.isRented());
//        if (accommodation.getNumRooms() > 0){
//            accommodation.setNumRooms(accommodation.getNumRooms()-1);
//        }
//        else{
//            throw new AccommodationFullException();
//        }
        return Optional.of(accommodationRepo.save(accommodation));
    }
}
