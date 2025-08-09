package com.example.java_proj1.service;

import com.example.java_proj1.domain.Lecture;
import com.example.java_proj1.repository.LectureRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
class LectureServiceTest {

    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;

    @Test
    void registerAndListLectures() {
        Lecture lecture = Lecture.builder()
                .title("Java + Spring Boot")
                .content("Basics of AI")
                .createdDate(LocalDate.now())
                .build();

        Long id = lectureService.register(lecture);
        List<Lecture> lectures = lectureService.findAll();

        Assertions.assertThat(lectures).extracting("title").contains("Java + Spring Boot");
    }

    @Test
    void updateLecture() {
        Lecture lecture = lectureRepository.save(Lecture.builder()
                .title("Old Title")
                .content("Old Content")
                .createdDate(LocalDate.now())
                .build());

        lectureService.update(lecture.getLectureId(), "New Title", "Updated Content");

        Lecture updated = lectureRepository.findById(lecture.getLectureId()).orElseThrow();
        Assertions.assertThat(updated.getTitle()).isEqualTo("New Title");
        Assertions.assertThat(updated.getContent()).isEqualTo("Updated Content");
    }
}
