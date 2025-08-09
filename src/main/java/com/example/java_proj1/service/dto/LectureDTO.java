package com.example.java_proj1.service.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class LectureDTO {
     Long lectureId;
     String title;
     String content;
     LocalDate createdDate;
}




