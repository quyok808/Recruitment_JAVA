package com.example.OnlyA.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
public class Recruiter {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String recruiterID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private String companyName;
    private String companyDescription;
    private String Image_Logo;
    private String Email;
    // Getters and setters
}
