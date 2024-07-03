package com.example.OnlyA.controller;

import com.example.OnlyA.model.Company;
import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.service.CompanyService;
import com.example.OnlyA.service.RecruiterService;
import com.example.OnlyA.service.jobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private jobPostingService JobPostingService; // replace with your service
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RecruiterService recruiterService;

    @GetMapping
    public String home(Model model) {
        List<JobPosting> jobPosting = JobPostingService.getAllPosts(); // replace with your method to get job positions
        List<Company> companies = companyService.getAllCompanies();
        List<Recruiter> recruiters = recruiterService.getAllRecruiters();
        model.addAttribute("recruiter", recruiters);
        model.addAttribute("company", companies);
        model.addAttribute("jobPostings", jobPosting);
        return "Home/index";
    }

}
