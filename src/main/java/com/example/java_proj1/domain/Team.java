package com.example.java_proj1.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long team_id; //Primary key

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
