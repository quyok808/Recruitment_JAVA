package com.example.OnlyA.repository;

import com.example.OnlyA.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepo extends JpaRepository<Role, String> {
    Role findRoleById(String id);
}

