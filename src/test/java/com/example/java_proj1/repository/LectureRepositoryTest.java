package com.example.java_proj1.repository;

import com.example.java_proj1.domain.Lecture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Transactional
class LectureRepositoryTest {

    @Autowired
    LectureRepository lectureRepository;

    @Test
    void saveAndFindById() {
        // given
        LocalDate date = LocalDate.parse("2025-08-08");
        Lecture lecture = Lecture.builder()
                .title("Software Dev")
                .content("Intro to SDLC")
                .createdDate(date)
                .build();

        // when
        Lecture savedLecture = lectureRepository.save(lecture);
        Long lectureId = savedLecture.getLectureId();

        // then
        Optional<Lecture> findLecture = lectureRepository.findById(lectureId);
        Assertions.assertThat(findLecture).isPresent();
        Assertions.assertThat(findLecture.get().getTitle()).isEqualTo("Software Dev");
        Assertions.assertThat(findLecture.get().getContent()).isEqualTo("Intro to SDLC");
        Assertions.assertThat(findLecture.get().getCreatedDate()).isEqualTo(date);
    }
}
