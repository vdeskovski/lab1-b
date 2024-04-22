package com.example.lab1b.repo;

import com.example.lab1b.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
}
