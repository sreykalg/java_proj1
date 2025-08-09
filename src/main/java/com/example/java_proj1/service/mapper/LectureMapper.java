package com.example.java_proj1.service.mapper;

import com.example.java_proj1.domain.Lecture;
import com.example.java_proj1.service.dto.LectureDTO;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {

    public LectureDTO toDto(Lecture lecture) {
        if (lecture == null) return null;

        return LectureDTO.builder()
                .lectureId(lecture.getLectureId())
                .title(lecture.getTitle())
                .content(lecture.getContent())
                .createdDate(lecture.getCreatedDate())
                .build();
    }

    public Lecture toEntity(LectureDTO dto) {
        if (dto == null) return null;

        return Lecture.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdDate(dto.getCreatedDate())
                .build();
    }
}
