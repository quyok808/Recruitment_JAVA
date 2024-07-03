package com.example.OnlyA.service;

import com.example.OnlyA.model.JobPosition;
import com.example.OnlyA.model.Application;
import com.example.OnlyA.repository.JobPositionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class JobPositionService {

    private final JobPositionRepository jobPositionRepository;

    /**
     * Retrieve all job positions from the database.
     *
     * @return a list of job positions
     */
    public List<JobPosition> getAllJobPositions() {
        return jobPositionRepository.findAll();
    }

    /**
     * Retrieve a job position by its id.
     *
     * @param id the id of the job position to retrieve
     * @return an Optional containing the found job position or empty if not found
     */
    public Optional<JobPosition> getJobPositionById(Long id) {
        return jobPositionRepository.findById(id);
    }

    /**
     * Save a new job position to the database.
     *
     * @param jobPosition the job position to add
     * @return the saved job position
     */
    public JobPosition saveJobPosition(JobPosition jobPosition) {
        return jobPositionRepository.save(jobPosition);
    }

    /**
     * Update an existing job position.
     *
     * @param id          the id of the job position to update
     * @param jobPosition the job position with updated information
     * @return the updated job position
     */
    public JobPosition updateJobPosition(Long id, @NotNull JobPosition jobPosition) {
        JobPosition existingJobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job position with ID " + id + " not found"));
        existingJobPosition.setTitle(jobPosition.getTitle());
        existingJobPosition.setDescription(jobPosition.getDescription());
        return jobPositionRepository.save(existingJobPosition);
    }

    /**
     * Delete a job position by its id.
     *
     * @param id the id of the job position to delete
     */
    public void deleteJobPosition(Long id) {
        jobPositionRepository.deleteById(id);
    }

    /**
     * Check if a job position with the given id exists.
     *
     * @param id the id of the job position to check
     * @return true if the job position exists, false otherwise
     */
    public boolean existsJobPosition(Long id) {
        return jobPositionRepository.existsById(id);
    }
}
