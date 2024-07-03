package com.example.OnlyA.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
public class Candidate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String candidateID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    private String Name;
    private String resume;

    // Getters and setters
}

