package com.example.OnlyA.controller;


import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.model.User;
import com.example.OnlyA.service.RecruiterService;
import com.example.OnlyA.service.jobPostingService;
import com.example.OnlyA.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tuyendung")
public class TuyenDungController {
    @Autowired
    private final jobPostingService JobPostingService;
    @Autowired
    private userService UserService;
    @Autowired
    private RecruiterService recruiterService;

    //    @PreAuthorize("hasAnyAuthority('ADMIN', 'BUSINESS')")
//    @GetMapping("/tuyendung")
//    public String tuyendung() {
//
//        return "TuyenDung/index";
//    }
    //them get TuyenDungPage 1/7/2024 (PostManagement)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'BUSINESS')")
    @GetMapping
    public String getTuyenDungPage(Model model, Principal principal) {
        String username = principal.getName();
        User user = UserService.timtheousername(username);
        Recruiter recruiterid = recruiterService.findRecruitersByUser(user);
        if (UserService.userHasCompany(username) == 1) {
            List<JobPosting> postLists = JobPostingService.getAllPostsByRecruiterID(recruiterid.getRecruiterID());
            model.addAttribute("postLists", postLists);
            int SLPost = postLists.size();
            model.addAttribute("SLPost", SLPost);
            model.addAttribute("SLUngVien", JobPostingService.SLUngTuyen(recruiterid.getRecruiterID()));
            return "TuyenDung/index";
        }
        return "redirect:/companies/add";
    }

    @GetMapping("/company-info")
    public String companyInfo() {
        return "TuyenDung/companyInfo";
    }

    @GetMapping("/post")
    public String showPostForm(Model model) {
        model.addAttribute("post", new JobPosting());
        return "TuyenDung/companyInfo";
    }
}
