package com.example.java_proj1.service;

import com.example.java_proj1.domain.*;
import com.example.java_proj1.repository.*;
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

    @Test
    void registerAndFindAll() {
        // given
        Team team = teamRepository.save(
                Team.builder()
                        .name("TeamUser")
                        .createdDate(LocalDate.now())
                        .build());

        Member member = memberRepository.save(
                Member.builder()
                        .name("aa")
                        .age(20)
                        .address("Street-101")
                        .createdDate(LocalDate.now())
                        .team(team)
                        .build());

        Lecture lecture = lectureRepository.save(
                Lecture.builder()
                        .title("Data Structures")
                        .content("Basic of DS")
                        .createdDate(LocalDate.now())
                        .build());

        Registration registration = Registration.builder()
                .registeredDate(LocalDate.now())
                .member(member)
                .lecture(lecture)
                .build();

        // when
        registrationService.register(registration);
        List<Registration> result = registrationService.findAll();

        // then
        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.get(0).getMember().getName()).isEqualTo("aa");
        Assertions.assertThat(result.get(0).getLecture().getTitle()).isEqualTo("Data Structures");
        Assertions.assertThat(result.get(0).getRegisteredDate()).isNotNull();
    }
}
