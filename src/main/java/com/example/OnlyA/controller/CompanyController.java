package com.example.OnlyA.controller;

import com.example.OnlyA.model.Company;
import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.model.User;
import com.example.OnlyA.repository.CompanyRepository;
import com.example.OnlyA.service.CompanyService;
import com.example.OnlyA.service.RecruiterService;
import com.example.OnlyA.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class CompanyController {
    private static final Logger LOGGER = Logger.getLogger(CompanyController.class.getName());
    @Value("${upload.dir}")
    private String uploadDir;
    @Autowired
    private final CompanyService companyService;
    @Autowired
    private final RecruiterService recruiterService;
    @Autowired
    private userService UserService;
    @Autowired
    private final CompanyRepository companyRepo;

    @GetMapping("/companies/add")
    public String showAddForm(Model model) {
        model.addAttribute("company", new Company());
        return "/companies/add-company";
    }

    @PostMapping("/companies/add")
    public String addCompany(@Valid Company company, BindingResult result, MultipartFile imageFile1, MultipartFile imageFile2, Model model) {
        if (result.hasErrors()) {
            return "/companies/add-company";
        }
        if (imageFile1 != null && !imageFile1.isEmpty()) {
            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String orgName = imageFile1.getOriginalFilename();
                String uniqueFileName = UUID.randomUUID().toString() + "_" + orgName;
                Path filePath = uploadPath.resolve(uniqueFileName);
                imageFile1.transferTo(filePath.toFile());
                company.setImagePath1(uniqueFileName);

                LOGGER.info("File uploaded successfully: " + filePath);
            } catch (IOException e) {
                LOGGER.severe("File upload failed: " + e.getMessage());
                model.addAttribute("message", "File upload failed: " + e.getMessage());
                return "/companies/add-company";
            }
        }
        if (imageFile2 != null && !imageFile2.isEmpty()) {
            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String orgName = imageFile2.getOriginalFilename();
                String uniqueFileName = UUID.randomUUID().toString() + "_" + orgName;
                Path filePath = uploadPath.resolve(uniqueFileName);
                imageFile2.transferTo(filePath.toFile());
                company.setImagePath2(uniqueFileName);

                LOGGER.info("File uploaded successfully: " + filePath);
            } catch (IOException e) {
                LOGGER.severe("File upload failed: " + e.getMessage());
                model.addAttribute("message", "File upload failed: " + e.getMessage());
                return "/companies/add-company";
            }
        }
        String username = UserService.getLoggedInUsername();
        Recruiter recruiter = recruiterService.findRecruitersByUser(UserService.timtheousername(username));


        companyService.addCompany(company);

        recruiter.setCompany(company);
        recruiterService.updateRecruiter(recruiter);
        User u = UserService.timtheousername(username);
        u.setHasCompany(1);
        UserService.updateHasCompany(u);
        return "redirect:/tuyendung";
    }

    @GetMapping("/companies")
    public String listCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "/companies/companies-list";
    }

    // GET request to show category edit form
    @GetMapping("/companies/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        model.addAttribute("company", company);
        return "/companies/update-company";
    }

    // POST request to update category
    @PostMapping("/companies/update/{id}")
    public String updateCompany(@PathVariable("id") Long id, @Valid Company company, BindingResult result,
                                MultipartFile imageFile1, MultipartFile imageFile2, Model model) {
        if (result.hasErrors()) {
            company.setId(id);
            return "/companies/update-category";
        }

        if (imageFile1 != null && !imageFile1.isEmpty()) {
            try {
                Company existingCompany = companyService.getCompanyById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

                if (imageFile1 != null && !imageFile1.isEmpty()) {
                    Path uploadPath = Paths.get(uploadDir);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    String orgName = imageFile1.getOriginalFilename();
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + orgName;
                    Path filePath = uploadPath.resolve(uniqueFileName);
                    imageFile1.transferTo(filePath.toFile());
                    existingCompany.setImagePath1(uniqueFileName);

                    LOGGER.info("File uploaded successfully: " + filePath);
                }

                // Update other product details
                existingCompany.setName(company.getName());
                existingCompany.setDescription(company.getDescription());
                companyService.updateCompany(existingCompany);
            } catch (IOException e) {
                LOGGER.severe("File upload failed: " + e.getMessage());
                model.addAttribute("message", "File upload failed: " + e.getMessage());
                model.addAttribute("company", company);
//                model.addAttribute("companies", companyService.getAllCompanies());
                return "/companies/update-company";
            }
        }
        if (imageFile2 != null && !imageFile2.isEmpty()) {
            try {
                Company existingCompany = companyService.getCompanyById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

                if (imageFile2 != null && !imageFile2.isEmpty()) {
                    Path uploadPath = Paths.get(uploadDir);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    String orgName = imageFile1.getOriginalFilename();
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + orgName;
                    Path filePath = uploadPath.resolve(uniqueFileName);
                    imageFile2.transferTo(filePath.toFile());
                    existingCompany.setImagePath2(uniqueFileName);

                    LOGGER.info("File uploaded successfully: " + filePath);
                }

                // Update other product details
                existingCompany.setName(company.getName());
                existingCompany.setDescription(company.getDescription());
                companyService.updateCompany(existingCompany);
            } catch (IOException e) {
                LOGGER.severe("File upload failed: " + e.getMessage());
                model.addAttribute("message", "File upload failed: " + e.getMessage());
                model.addAttribute("company", company);
//                model.addAttribute("companies", companyService.getAllCompanies());
                return "/companies/update-company";
            }
        }
        companyService.updateCompany(company);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "redirect:/companies";
    }

    // GET request for deleting category
    @GetMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        companyService.deleteCompanyById(id);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "redirect:/companies";
    }

    @GetMapping("/companies/detail/{id}")
    public String companyDetail(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id: " + id));
        model.addAttribute("company", company);
        return "/companies/company-detail";
    }

    @GetMapping("/company/{id}")
    public String getCompany(@PathVariable Long id, Model model) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
        model.addAttribute("company", company);
        return "company";
    }
}
