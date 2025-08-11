package com.example.java_proj1.service;

import com.example.java_proj1.domain.Member;
import com.example.java_proj1.domain.Team;
import com.example.java_proj1.repository.LectureRepository;
import com.example.java_proj1.repository.MemberRepository;
import com.example.java_proj1.repository.RegistrationRepository;
import com.example.java_proj1.repository.TeamRepository;
import com.example.java_proj1.service.dto.RegistrationDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
class RegistrationServiceTest {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    @Test
    void registerAndFindAll() {
        // given
        Team team = teamRepository.save(
                Team.builder()
                        .name("TeamUser")
                        .createdDate(LocalDate.now())
                        .build());

        var member = memberRepository.save(
                Member.builder()
                        .name("aa")
                        .age(20)
                        .address("Street-101")
                        .createdDate(LocalDate.now())
                        .team(team)
                        .build());

        var lecture = lectureRepository.save(
                com.example.java_proj1.domain.Lecture.builder()
                        .title("Data Structures")
                        .content("Basic of DS")
                        .createdDate(LocalDate.now())
                        .build());

        RegistrationDTO registrationDTO = RegistrationDTO.builder()
                .memberId(member.getMemberId())
                .lectureId(lecture.getLectureId())
                .registeredDate(LocalDate.now())
                .build();

        // when
        RegistrationDTO saved = registrationService.register(registrationDTO);

        List<RegistrationDTO> allRegistrations = registrationService.findAll();

        // then
        Assertions.assertThat(allRegistrations).hasSize(1);

        RegistrationDTO dto = allRegistrations.get(0);
        Assertions.assertThat(dto.getMemberId()).isEqualTo(member.getMemberId());
        Assertions.assertThat(dto.getLectureId()).isEqualTo(lecture.getLectureId());
        Assertions.assertThat(dto.getRegisteredDate()).isNotNull();

        Assertions.assertThat(saved.getMemberId()).isEqualTo(member.getMemberId());
        Assertions.assertThat(saved.getLectureId()).isEqualTo(lecture.getLectureId());
    }
}
