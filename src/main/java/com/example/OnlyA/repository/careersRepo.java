package com.example.OnlyA.repository;

import com.example.OnlyA.model.Careers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface careersRepo extends JpaRepository<Careers, Long> {
}
