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
    private String Kinhnghiem;
    private int trangthai = 1;
    @ManyToOne
    @JoinColumn(name = "JobpositionID")
    private JobPosition jobposition;
    private String ChucVu;
    private String HinhThucLamViec;
    private long MucLuong;
    private String Author;
    private int totalApplications = 0;
    private int totalHired = 0;
    // Getters and setters
}

