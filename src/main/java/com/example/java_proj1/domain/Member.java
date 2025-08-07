package com.example.java_proj1.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long member_id; //Primary key

    @ManyToOne //Many member belong to one team
    @JoinColumn(name = "team_id")  // Foreign key in Member referencing Team.team_id
    private Team team;

    @OneToMany(mappedBy = "member") // One team can have multiple members
    List<Registration> registrations = new ArrayList<>();
}
