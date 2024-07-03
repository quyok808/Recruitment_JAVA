package com.example.OnlyA.controller;

import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.model.User;
import com.example.OnlyA.service.RecruiterService;
import com.example.OnlyA.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

    private final userService userService;

    @Autowired
    private RecruiterService recruiterService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "Account/test";
    }

    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "Account/test";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        // Thêm xử lý đăng nhập thực tế ở đây

        // Nếu đăng nhập thất bại, trả về form đăng nhập với lỗi
        model.addAttribute("error", "Invalid username or password");
        return "Account/test";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, @NotNull BindingResult bindingResult, @NotNull @RequestParam("accountType") String accountType, Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "Account/test";
        }
        userService.save(user);
        userService.setDefaultRole(user.getUsername(), accountType);
        Recruiter recruiter = new Recruiter();
        recruiter.setUser(user);
        recruiterService.addRecruiter(recruiter);
        return "redirect:/login";
    }

}
