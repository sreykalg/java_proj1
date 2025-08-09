package com.example.java_proj1.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MemberDTO {
    Long memberId;
    String name;
    int age;
    String address;
    LocalDate createdDate;
    Long teamId;
}
