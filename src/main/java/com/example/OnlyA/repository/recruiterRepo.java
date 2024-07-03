package com.example.OnlyA.repository;

import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface recruiterRepo extends JpaRepository<Recruiter, String> {
    Optional<Recruiter> findById(String recruiterID);

    List<Recruiter> findByUser(User user);
}
