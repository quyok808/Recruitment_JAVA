package com.example.OnlyA.controller;

import com.example.OnlyA.model.JobPosting;
import com.example.OnlyA.service.RecruiterService;
import com.example.OnlyA.service.jobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;


//them controller TuyenDungPage 1/7/2024 (PostManagement)

@Controller
public class PostController {
    @Autowired
    private jobPostingService JobPostService;

    @Autowired
    private RecruiterService recruiterService;

    private static final Logger LOGGER = Logger.getLogger(PostController.class.getName());

    @GetMapping("/addJobPost")
    public String showAddJobPostForm(Model model) {
        model.addAttribute("jobPost", new JobPosting());
        model.addAttribute("recruiters", recruiterService.getAllRecruiters());
        return "Post/formAddPost";
    }

    @PostMapping("/addJobPost")
    public String addJobPost(@ModelAttribute JobPosting jobPost) {
        jobPost.setDatePosted(LocalDate.now());
        JobPostService.addJobPosting(jobPost);
        return "redirect:/tuyendung";
    }

    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteJobPosting(@PathVariable String id) {
        JobPostService.deleteJobPostingById(id);
        return "redirect:/tuyendung";
    }

    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        JobPosting post = JobPostService.getJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("jobPost", post);
        model.addAttribute("company", recruiterService.getAllRecruiters());
        return "Post/update-jobpost";
    }

    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable String id, @Valid JobPosting post,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("jobPost", post);
            model.addAttribute("company", recruiterService.getAllRecruiters());
            return "Post/update-jobpost";
        }

        JobPosting existingPost = JobPostService.getJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        // Update other product details
        existingPost.setJobTitle(post.getJobTitle());
        existingPost.setJobDescription(post.getJobDescription());
        existingPost.setLocation(post.getLocation());


        JobPostService.updateJobPosting(existingPost);

        return "redirect:/tuyendung";
    }

    @GetMapping("/detail/{id}")
    public String Detail(@PathVariable("id") String id, Model model) {
        JobPosting temp = null;
        for (JobPosting item : JobPostService.getAllPosts()) {
            if (Objects.equals(item.getJobID(), id)) {
                temp = item;
            }
        }
        if (temp != null) {
            model.addAttribute("jobPost", temp);
            return "Post/Detail";
        } else {
            return "Khong co bai viet nay !!!";
        }
    }
}
