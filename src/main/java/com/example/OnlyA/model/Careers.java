package com.example.OnlyA.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Careers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CareersID;

    private String Name;

}
