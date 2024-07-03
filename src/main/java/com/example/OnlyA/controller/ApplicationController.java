package com.example.OnlyA.controller;

import com.example.OnlyA.model.Application;
import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private final ApplicationService applicationService;

    @GetMapping
    public String ShowApplications(Model model) {
        List<Application> appliList = applicationService.getAllApplicant();
        model.addAttribute("appliList", appliList);
        return "Application/index";
    }

    @PostMapping("/handleApplication")
    public String handleApplication(@RequestParam String action, @RequestParam String applicationId) {
        if ("accept".equals(action)) {
            applicationService.acceptApplication(applicationId);
        } else if ("reject".equals(action)) {
            applicationService.rejectApplication(applicationId);
        }
        // Return a view name or redirect to appropriate page after handling the action
        return "redirect:/Application/index"; // Redirect to index page or any other appropriate page
    }
}
