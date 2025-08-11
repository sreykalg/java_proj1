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
public class RegistrationDTO {

     Long registrationId;
     Long memberId;
     Long lectureId;
     LocalDate registeredDate;

     // added
     private MemberDTO member;
     private LectureDTO lecture;

     public String getMemberName() {
          return member != null ? member.getName() : "";
     }

     public String getTeamName() {
          return member != null && member.getTeam() != null ? member.getTeam().getName() : "";
     }

     public String getLectureTitle() {
          return lecture != null ? lecture.getTitle() : "";
     }
}
