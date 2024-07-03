package com.example.OnlyA.service;

import com.example.OnlyA.model.Application;
import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.repository.applicationRepo;
import com.example.OnlyA.repository.jobPostingRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {
    private final applicationRepo ApplicationRepository;
    private final jobPostingRepo jobPostingRepository;

    public List<Application> getAllApplicant() {
        return ApplicationRepository.findAll();
    }

    public List<Application> getAllApplicationsByJobpostingID(String jobID) {
        JobPosting jobposting = jobPostingRepository.findById(jobID)
                .orElseThrow(() -> new RuntimeException("Job Posting not found"));
        return ApplicationRepository.findByJob(jobposting);
    }


    public void acceptApplication(String applicationId) {
        Application application = ApplicationRepository.findById(applicationId).orElse(null);
        if (application != null) {
            // Thiết lập trạng thái nhận
            application.setStatus(1); // Hoặc sử dụng Enum để biểu diễn trạng thái
            ApplicationRepository.save(application);
        }
    }

    public void rejectApplication(String applicationId) {
        // Lấy đối tượng Application từ cơ sở dữ liệu
        Application application = ApplicationRepository.findById(applicationId).orElse(null);
        if (application != null) {
            // Thiết lập trạng thái từ chối
            application.setStatus(-1); // Hoặc sử dụng Enum để biểu diễn trạng thái
            ApplicationRepository.save(application);
        }
    }
}
