package com.example.OnlyA.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private String token;
    private String tokenPurpose;
    private LocalDateTime expiryDate;

    // Getters and setters
}

