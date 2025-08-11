package com.example.java_proj1.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class LectureDTO {
     Long lectureId;
     String title;
     String content;
     LocalDate createdDate;
}




