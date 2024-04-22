package com.example.lab1b.repo;

import com.example.lab1b.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccommodationRepo extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findAllByRentedIsTrue();
    List<Accommodation> findAllByRentedIsFalse();
}
