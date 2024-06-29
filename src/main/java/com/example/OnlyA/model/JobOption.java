package com.example.OnlyA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class JobOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long joboptionID;

    private String Name;
    private String Type;
}
