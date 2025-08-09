package com.example.java_proj1.service.mapper;

import com.example.java_proj1.domain.Registration;
import com.example.java_proj1.service.dto.RegistrationDTO;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public RegistrationDTO toDto(Registration registration) {
        if (registration == null) return null;

        return RegistrationDTO.builder()
                .registrationId(registration.getRegistrationId())
                .registeredDate(registration.getRegisteredDate())
                .memberId(registration.getMember().getMemberId())
                .lectureId(registration.getLecture().getLectureId())
                .build();
    }
}
