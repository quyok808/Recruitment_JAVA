package com.example.OnlyA.controller;

import com.example.OnlyA.model.Candidate;
import com.example.OnlyA.model.JobPosition;
import com.example.OnlyA.service.CandidateService;
import com.example.OnlyA.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JobPositionService jobPositionService;

    @GetMapping
    public String getProfile(Model model) {
        Candidate candidate = candidateService.getCurrentCandidate();
        if (candidate != null) {
            model.addAttribute("candidate", candidate);
            return "Candidate/candidateProfile";
        } else {
            model.addAttribute("candidate", new Candidate());
            model.addAttribute("message", "Vui lòng cập nhật hồ sơ của bạn.");
            return "Candidate/addProfile";
        }
    }

    @GetMapping("/edit")
    public String editProfile(Model model) {
        Candidate candidate = candidateService.getCurrentCandidate();
        if (candidate != null) {
            model.addAttribute("candidate", candidate);
            return "Candidate/editProfile";
        } else {
            return "redirect:/candidate";
        }
    }

    @PostMapping("/add")
    public String addProfile(@ModelAttribute("candidate") @Validated Candidate candidate,
                             @RequestParam("cvFile") MultipartFile cvFile,
                             @RequestParam("certificateFile") MultipartFile certificateFile,
                             @RequestParam("awardFile") MultipartFile awardFile) {
        candidateService.addCandidateProfile(candidate, cvFile, certificateFile, awardFile);
        return "redirect:/candidate";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("candidate") @Validated Candidate candidate,
                                @RequestParam("cvFile") MultipartFile cvFile,
                                @RequestParam("certificateFile") MultipartFile certificateFile,
                                @RequestParam("awardFile") MultipartFile awardFile) {
        candidateService.updateCandidateProfile(candidate, cvFile, certificateFile, awardFile);
        return "redirect:/candidate";
    }

    @ModelAttribute("jobpositions")
    public List<JobPosition> populateJobPositions() {
        return jobPositionService.getAllJobPositions();
    }
}
