package com.example.java_proj1.service.mapper;

import com.example.java_proj1.domain.Registration;
import com.example.java_proj1.service.dto.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {
    private final MemberMapper memberMapper;

    public RegistrationMapper(MemberMapper memberMapper, LectureMapper lectureMapper) {
        this.memberMapper = memberMapper;
        this.lectureMapper = lectureMapper;
    }

    private final LectureMapper lectureMapper;

    public RegistrationDTO toDto(Registration registration) {
        if (registration == null) return null;

        return RegistrationDTO.builder()
                .registrationId(registration.getRegistrationId())
                .memberId(registration.getMember() != null ? registration.getMember().getMemberId() : null)
                .lectureId(registration.getLecture() != null ? registration.getLecture().getLectureId() : null)
                .registeredDate(registration.getRegisteredDate())
                .member(memberMapper.toDto(registration.getMember()))
                .lecture(lectureMapper.toDto(registration.getLecture()))
                .build();
    }

    // added
    public Registration toEntity(RegistrationDTO dto) {
        if (dto == null) return null;
        return Registration.builder()
                .registeredDate(dto.getRegisteredDate())
                .build();
    }
}
