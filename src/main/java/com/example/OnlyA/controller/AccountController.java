package com.example.OnlyA.controller;

import com.example.OnlyA.model.User;
import com.example.OnlyA.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {
    private final userService userSV;

    @GetMapping("/login")
    public String account() {
        return "Account/Login"; // Assuming this is your login page
    }


    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new User()); // Thêm một đối tượng User mới vào model
        return "Account/Login";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute("user") User user,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "Account/Login"; // Return registration form with errors
        }

        // Save user and redirect to login page
        userSV.save(user);
        userSV.setDefaultRole(user.getUsername(), user.getAccountType());
        return "redirect:/account"; // Redirect to login page
    }
}

