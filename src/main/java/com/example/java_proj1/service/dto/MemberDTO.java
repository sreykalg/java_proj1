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
public class MemberDTO {
    Long memberId;
    String name;
    int age;
    String address;
    LocalDate createdDate;

    Long teamId;
    TeamDTO team;
}
