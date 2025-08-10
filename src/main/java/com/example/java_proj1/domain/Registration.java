package com.example.java_proj1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "registration")
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
    @Column(name = "registration_id")
    private Long registrationId;

    // information on the date of taking the lecture is required
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registeredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
