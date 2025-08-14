package com.example.java_proj1.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter // ref lecture
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "team") // ref lecture
public class Team {

    // requirement :
    /*
    * It must have team registration, modification, and list functions.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id") // ref lecture
    private Long teamId;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd") // ref lecture
    private LocalDate createdDate;

    @Builder.Default
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();
}

