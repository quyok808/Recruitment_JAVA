package com.example.OnlyA.controller;


import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.service.jobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tuyendung")
public class TuyenDungController {
    @Autowired
    private final jobPostingService JobPostingService;

    @GetMapping
    public String getTuyenDungPage(Model model) {
        List<JobPosting> postLists = JobPostingService.getAllPostsByRecruiterID("B456");
        model.addAttribute("postLists", postLists);
        int SLPost = postLists.size();
        model.addAttribute("SLPost", SLPost);
        model.addAttribute("SLUngVien", JobPostingService.SLUngTuyen("B456"));
        return "TuyenDung/index";
    }

    @GetMapping("/company-info")
    public String companyInfo(){
        return "TuyenDung/companyInfo";
    }

    @GetMapping("/post")
    public String showPostForm(Model model){
        model.addAttribute("post", new JobPosting());
        return "TuyenDung/companyInfo";
    }
}
