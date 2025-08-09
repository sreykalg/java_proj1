package com.example.java_proj1.service.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class RegistrationDTO {

     Long registrationId;
     Long memberId;
     Long lectureId;
     LocalDate registeredDate;

}
