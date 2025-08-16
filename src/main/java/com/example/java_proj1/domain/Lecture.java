package com.example.java_proj1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
// In `Entity class`, try to avoid using `Setter` as much as possible.
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
// If the table name and class name are the same, there is no need to write the name separately in `Table`.
@Table(name = "lecture")
public class Lecture {
    /* requirement :
    * The lecture must have title, content, and creation date information.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id") // If the field name and column name are the same, there is no need to write the name separately in `name`.
    private Long lectureId;

    private String title;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd") // why use this annotation ?
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
