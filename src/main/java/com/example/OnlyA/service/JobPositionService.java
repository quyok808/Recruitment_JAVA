package com.example.OnlyA.service;

import com.example.OnlyA.model.JobPosition;
import com.example.OnlyA.repository.JobPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPositionService {

    @Autowired
    private JobPositionRepository jobPositionRepository;

    public Optional<JobPosition> getJobPositionById(Long id) {
        return jobPositionRepository.findById(id);
    }

    public List<JobPosition> getAllJobPositions() {
        return jobPositionRepository.findAll();
    }

    public JobPosition saveJobPosition(JobPosition jobPosition) {
        return jobPositionRepository.save(jobPosition);
    }

    public JobPosition updateJobPosition(Long id, JobPosition jobPosition) {
        Optional<JobPosition> optionalJobPosition = jobPositionRepository.findById(id);
        if (optionalJobPosition.isPresent()) {
            JobPosition existingJobPosition = optionalJobPosition.get();
            existingJobPosition.setTitle(jobPosition.getTitle());
            existingJobPosition.setDescription(jobPosition.getDescription());
            return jobPositionRepository.save(existingJobPosition);
        } else {
            throw new RuntimeException("Job position with ID " + id + " not found");
        }
    }

    public void deleteJobPosition(Long id) {
        jobPositionRepository.deleteById(id);
    }

    public boolean existsJobPosition(Long id) {
        return jobPositionRepository.existsById(id);
    }
}
