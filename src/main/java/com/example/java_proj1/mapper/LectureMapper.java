package com.example.java_proj1.mapper;

@Mapper(componentModel = "spring")
public interface LectureMapper {

    LectureDTO toDto(Lecture entity);

    Lecture toEntity(LectureDTO dto);
}
