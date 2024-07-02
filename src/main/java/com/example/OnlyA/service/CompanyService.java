package com.example.OnlyA.service;

import com.example.OnlyA.model.Company;
import com.example.OnlyA.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public void updateCompany(@NotNull Company company) {
        Company existingCompany= companyRepository.findById(company.getId())
                .orElseThrow(() -> new IllegalStateException("Company with ID " +
                        company.getId() + " does not exist."));
        existingCompany.setName(company.getName());
        existingCompany.setDescription(company.getDescription());
        companyRepository.save(existingCompany);
    }

    public void deleteCompanyById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new IllegalStateException("Company with ID " + id + " does not exist.");
        }
        companyRepository.deleteById(id);
    }


}
