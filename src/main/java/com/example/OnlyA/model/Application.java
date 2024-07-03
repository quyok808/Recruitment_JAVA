package com.example.OnlyA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application {
    @Id
    private String applicationID;

    @ManyToOne
    @JoinColumn(name = "candidateID")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "jobID")
    private JobPosting job;

    private LocalDate applicationDate;
    private int status;

    // Getters and setters
}
