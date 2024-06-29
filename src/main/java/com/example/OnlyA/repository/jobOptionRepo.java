package com.example.OnlyA.repository;

import com.example.OnlyA.model.JobOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jobOptionRepo extends JpaRepository<JobOption, Long> {
}
