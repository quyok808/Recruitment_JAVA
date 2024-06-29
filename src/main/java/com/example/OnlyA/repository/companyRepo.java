package com.example.OnlyA.repository;

import com.example.OnlyA.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface companyRepo extends JpaRepository<Companies, Long> {
}
