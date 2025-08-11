package com.example.java_proj1.service;

import com.example.java_proj1.service.dto.LectureDTO;
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
        LectureDTO dto = LectureDTO.builder()
                .title("Java + Spring Boot")
                .content("Basics of AI")
                .createdDate(LocalDate.now())
                .build();

        LectureDTO savedDto = lectureService.register(dto);
        List<LectureDTO> lectures = lectureService.findAll();

        Assertions.assertThat(lectures).extracting("title").contains("Java + Spring Boot");
        Assertions.assertThat(savedDto.getLectureId()).isNotNull();
    }

    @Test
    void updateLecture() {

        LectureDTO dto = LectureDTO.builder()
                .title("Old Title")
                .content("Old Content")
                .createdDate(LocalDate.now())
                .build();

        LectureDTO savedDto = lectureService.register(dto);

        LectureDTO updateDto = LectureDTO.builder()
                .title("New Title")
                .content("Updated Content")
                .build();

        LectureDTO updatedDto = lectureService.update(savedDto.getLectureId(), updateDto);

        Assertions.assertThat(updatedDto.getTitle()).isEqualTo("New Title");
        Assertions.assertThat(updatedDto.getContent()).isEqualTo("Updated Content");
    }
}
