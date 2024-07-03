package com.example.OnlyA.service;

import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.repository.jobPostingRepo;
import com.example.OnlyA.repository.recruiterRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//them service 1/7/2024 (PostManagement)

@Service
@RequiredArgsConstructor
@Transactional
public class jobPostingService {
    private final jobPostingRepo jobPostingRepository;
    private final recruiterRepo RecruiterRepository;

    public List<JobPosting> getAllPosts() {
        return jobPostingRepository.findAll();
    }

    public List<JobPosting> getAllPostsByRecruiterID(String recruiterID) {
        Optional<Recruiter> recruiter = RecruiterRepository.findById(recruiterID);
        //                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
        if (recruiter.isEmpty())
            return new ArrayList<>();

        return jobPostingRepository.findByRecruiter(recruiter);

    }

    public int SLUngTuyen(String recruiterID) {
        List<JobPosting> Posts = getAllPostsByRecruiterID(recruiterID);
        int SL = 0;
        for (JobPosting p : Posts) {
            SL += p.getTotalApplications();
        }
        return SL;
    }

    public Optional<JobPosting> getJobById(String id) {
        return jobPostingRepository.findById(id);
    }

    // Add a new product to the database
    public JobPosting addJobPosting(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }

    // Update an existing product
    public JobPosting updateJobPosting(@NotNull JobPosting jobposting) {
        JobPosting existingJobPost = jobPostingRepository.findById(jobposting.getJobID())
                .orElseThrow(() -> new IllegalStateException("Job Posting with ID " + jobposting.getJobID() + " does not exist."));

        return jobPostingRepository.save(existingJobPost);
    }

    // Delete a product by its id
    public void deleteJobPostingById(String id) {
        if (!jobPostingRepository.existsById(id)) {
            throw new IllegalStateException("Job posting with ID " + id + " does not exist.");
        }
        jobPostingRepository.deleteById(id);
    }

    public List<JobPosting> searchJobPostings(String keyword) {
        return jobPostingRepository.findByJobTitleContaining(keyword);
    }
}
