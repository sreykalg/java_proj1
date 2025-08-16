package com.example.java_proj1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter // ref Lecture
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "member") // ref Lecture
public class Member {

    /* Requirement:
    * The member must have member name, age, address, and creation date information.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id") // ref Lecture
    private Long memberId;

    // I think it would be better to use this constructor in the form `public static Member of(String name, ..)`
    // Constructor
    public Member(Long memberId, String name, int age, String address, LocalDate createdDate) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.createdDate = createdDate;
    }

    private String name;
    private int age;
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd") // I think this is not needed
    private LocalDate createdDate;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder.Default
    @OneToMany(mappedBy = "member",  fetch = FetchType.LAZY)
    private List<Registration> registrations = new ArrayList<>();

    // change name
    public void changeName(String name) {
        this.name = name;
    }

    // change age
    public void changeAge(int age) {
        this.age = age;
    }

    // change address
    public void changeAddress(String address) {
        this.address = address;
    }

    // change Team
    public void changeTeam(Team team){this.team = team;}
}
