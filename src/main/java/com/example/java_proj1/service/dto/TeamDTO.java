package com.example.java_proj1.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TeamDTO {
     Long teamId;
     String name;
     LocalDate createdDate;
}