package com.example.java_proj1.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lecture {
    @Id
    @GeneratedValue
    private Long lecture_id;

    @OneToMany(mappedBy = "lecture")
    private List<Registration> registrations = new ArrayList<>();
}
