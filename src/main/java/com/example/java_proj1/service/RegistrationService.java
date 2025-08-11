package com.example.java_proj1.service;

import com.example.java_proj1.domain.Lecture;
import com.example.java_proj1.domain.Member;
import com.example.java_proj1.domain.Registration;
import com.example.java_proj1.repository.LectureRepository;
import com.example.java_proj1.repository.MemberRepository;
import com.example.java_proj1.repository.RegistrationRepository;
import com.example.java_proj1.service.dto.RegistrationDTO;
import com.example.java_proj1.service.mapper.RegistrationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;
    private final RegistrationMapper registrationMapper;

    @Transactional
    public RegistrationDTO register(RegistrationDTO dto) {
        Registration registration = registrationMapper.toEntity(dto);

        // Set associations
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + dto.getMemberId()));
        Lecture lecture = lectureRepository.findById(dto.getLectureId())
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found: " + dto.getLectureId()));

        registration.setMember(member);
        registration.setLecture(lecture);

        registrationRepository.save(registration);
        return registrationMapper.toDto(registration);
    }

    public List<RegistrationDTO> findAll() {
        return registrationRepository.findAll()
                .stream()
                .map(registrationMapper::toDto)
                .toList();
    }
}
