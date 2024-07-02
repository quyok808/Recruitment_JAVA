package com.example.OnlyA.service;

import com.example.OnlyA.model.Company;
import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.model.User;
import com.example.OnlyA.repository.recruiterRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
//them service 1/7/2024 (PostManagement)

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

    public Recruiter findRecruitersByUser(User user) {
        List<Recruiter> recruiters = RecruiterRepository.findByUser(user);
        if (recruiters.isEmpty()) {
            throw new NoSuchElementException("No recruiters found for user: " + user.getUsername());
        }
        return recruiters.getFirst();
    }

    public void addRecruiter(Recruiter recruiter) {
        RecruiterRepository.save(recruiter);
    }

    public void updateRecruiter(@NotNull Recruiter recruiter) {
        Recruiter existingRecruiter = RecruiterRepository.findById(recruiter.getRecruiterID())
                .orElseThrow(() -> new IllegalStateException("Company with ID " +
                        recruiter.getRecruiterID() + " does not exist."));
        existingRecruiter.setCompany(recruiter.getCompany());

        RecruiterRepository.save(existingRecruiter);
    }
}
