package com.example.OnlyA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String experience;
    private String major;
    private String skills;

    private String cvFileName;
    private String certificateFileName;
    private String awardFileName;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne
    @JoinColumn(name = "jobposition_id")
    private JobPosition jobposition;
}
