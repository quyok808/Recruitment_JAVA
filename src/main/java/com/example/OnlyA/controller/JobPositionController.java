package com.example.OnlyA.controller;

import com.example.OnlyA.model.Company;
import com.example.OnlyA.model.JobPosition;
import com.example.OnlyA.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/job-positions")
public class JobPositionController {

    @Autowired
    private JobPositionService jobPositionService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("jobPosition", new JobPosition());
        return "/JobPosition/add-job-position";
    }

    @PostMapping("/add")
    public String addJobPosition(@Valid JobPosition jobPosition, BindingResult result) {
        if (result.hasErrors()) {
            return "/JobPosition/add-job-position";
        }
        jobPositionService.saveJobPosition(jobPosition);
        return "redirect:/job-positions";
    }

    @GetMapping
    public String listJobPositions(Model model) {
        List<JobPosition> jobPositions = jobPositionService.getAllJobPositions();
        model.addAttribute("jobPositions", jobPositions);
        return "/JobPosition/job-positions-list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        JobPosition jobPosition = jobPositionService.getJobPositionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid job position Id:" + id));
        model.addAttribute("jobPosition", jobPosition);
        return "/JobPosition/update-job-position";
    }

    @PostMapping("/update/{id}")
    public String updateJobPosition(@PathVariable("id") Long id, @Valid JobPosition jobPosition,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            jobPosition.setId(id);
            return "/JobPosition/update-job-position";
        }
        jobPositionService.updateJobPosition(id, jobPosition);
        model.addAttribute("jobPositions", jobPositionService.getAllJobPositions());
        return "redirect:/job-positions";
    }

    @GetMapping("/delete/{id}")
    public String deleteJobPosition(@PathVariable("id") Long id, Model model) {
        JobPosition jobPosition = jobPositionService.getJobPositionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid job position Id:" + id));
        jobPositionService.deleteJobPosition(id);
        model.addAttribute("jobPositions", jobPositionService.getAllJobPositions());
        return "redirect:/job-positions";
    }

    @GetMapping("/detail/{id}")
    public String PositionDetail(@PathVariable("id") Long id, Model model) {
        JobPosition jobPosition = jobPositionService.getJobPositionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id: " + id));
        model.addAttribute("positions", jobPosition);
        return "/JobPosition/jobposition-detail";
    }
}
