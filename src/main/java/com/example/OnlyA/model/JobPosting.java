package com.example.OnlyA.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter

@Entity
public class JobPosting {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String jobID;

    @ManyToOne
    @JoinColumn(name = "recruiterID")
    private Recruiter recruiter;

    private String jobTitle;
    private String jobDescription;
    private String location;
    private LocalDate datePosted;
    private int totalApplications = 0;
    private int totalHired = 0;
    private int Trangthai;
    // Getters and setters
}

