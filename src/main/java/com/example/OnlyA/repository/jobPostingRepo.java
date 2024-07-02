package com.example.OnlyA.repository;

import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface jobPostingRepo extends JpaRepository<JobPosting, String> {
    List<JobPosting> findByRecruiter(Optional<Recruiter> recruiter);
}
