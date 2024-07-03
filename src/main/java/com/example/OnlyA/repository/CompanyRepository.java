package com.example.OnlyA.repository;
import com.example.OnlyA.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
}
