package com.example.OnlyA.repository;

import com.example.OnlyA.model.Application;
import com.example.OnlyA.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface applicationRepo extends JpaRepository<Application, String> {
    List<Application> findByJob(JobPosting job);
}
