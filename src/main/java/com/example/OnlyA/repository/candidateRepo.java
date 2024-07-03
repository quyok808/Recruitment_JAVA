package com.example.OnlyA.repository;

import com.example.OnlyA.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface candidateRepo extends JpaRepository<Candidate, String> {
}
