package com.example.lab1b.repo;

import com.example.lab1b.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepo extends JpaRepository<Host,Long> {
}
