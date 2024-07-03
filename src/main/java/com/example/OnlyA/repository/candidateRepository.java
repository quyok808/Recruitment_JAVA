package com.example.OnlyA.repository;

import com.example.OnlyA.model.Candidate;
import com.example.OnlyA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface candidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByUser(User user);
}
