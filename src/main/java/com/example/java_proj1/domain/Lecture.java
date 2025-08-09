package com.example.java_proj1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "lecture")
public class Lecture {
    /* requirement :
    * The lecture must have title, content, and creation date information.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long lectureId;

    private String title;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @Builder.Default
    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
    private List<Registration> registrations = new ArrayList<>();

    // change title
    public void changeTitle(String title) {
        this.title = title;
    }

    // change content
    public void changeContent(String content) {
        this.content = content;
    }
}
