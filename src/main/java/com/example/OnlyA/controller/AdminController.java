package com.example.OnlyA.controller;

import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.service.jobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final jobPostingService JobPostingService;

    @GetMapping
    public String getTuyenDungPage(Model model) {
        List<JobPosting> postLists = JobPostingService.getAllPostsByRecruiterID("B456");
        model.addAttribute("postLists", postLists);
        return "TuyenDung/index";
    }
}
