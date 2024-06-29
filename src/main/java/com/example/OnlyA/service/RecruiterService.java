package com.example.OnlyA.service;

import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.repository.recruiterRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruiterService {
    private final recruiterRepo RecruiterRepository;

    public List<Recruiter> getAllRecruiters() {
        return RecruiterRepository.findAll();
    }

    public Optional<Recruiter> findById(String id) {
        return RecruiterRepository.findById(id);
    }
}
