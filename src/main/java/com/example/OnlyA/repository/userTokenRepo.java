package com.example.OnlyA.repository;

import com.example.OnlyA.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userTokenRepo extends JpaRepository<UserToken, Long> {
}

