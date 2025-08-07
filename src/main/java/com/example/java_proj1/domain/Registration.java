package com.example.java_proj1.domain;

import jakarta.persistence.*;

@Entity
public class Registration {
    @Id
    @GeneratedValue
    private Long registration_id;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
