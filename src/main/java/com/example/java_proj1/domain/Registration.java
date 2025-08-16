package com.example.java_proj1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter // ref lecture
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "registration") // ref lecture
public class Registration {

    /* requirement:
    *Information on the date of taking the lecture is required.
    *
    * - It must have team registration, modification, and list functions.
    - It must have member registration, modification, and list functions.
    - You must select a team when registering a member.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id") // ref lecture
    private Long registrationId;

    @JsonFormat(pattern = "yyyy-MM-dd") // I think this is not needed
    private LocalDate registeredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
