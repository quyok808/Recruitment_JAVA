package com.example.OnlyA.repository;

import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.model.User_Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface user_jobRepo extends JpaRepository<User_Job, String> {
    List<User_Job> findByJob(JobPosting job);
}
