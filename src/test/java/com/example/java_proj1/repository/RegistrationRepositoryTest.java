package com.example.java_proj1.repository;

import com.example.java_proj1.domain.Lecture;
import com.example.java_proj1.domain.Member;
import com.example.java_proj1.domain.Registration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
class RegistrationRepositoryTest {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Test
    void findByMemberId() {
        // given
        Member member = memberRepository.save(
                new Member(null, "Sooah", 22, "Street-100", LocalDate.of(2025, 8, 7)));

        Lecture lecture = lectureRepository.save(
                Lecture.builder()
                        .title("Computer Networking")
                        .content("Networking basics")
                        .createdDate(LocalDate.of(2025, 8, 7))
                        .build()
        );

        registrationRepository.save(
                Registration.builder()
                        .member(member)
                        .lecture(lecture)
                        .registeredDate(LocalDate.of(2025, 8, 7))
                        .build()
        );

        // when
        List<Registration> registrations = registrationRepository.findByMember_MemberId(member.getMemberId());

        // then
        Assertions.assertThat(registrations).hasSize(1);
        Assertions.assertThat(registrations.get(0).getLecture().getTitle()).isEqualTo("Computer Networking");
    }

    @Test
    void findByLectureId() {
        // given
        Member member = memberRepository.save(
                new Member(null, "Suai", 23, "Street-200", LocalDate.of(2025, 8, 8)));

        Lecture lecture = lectureRepository.save(
                Lecture.builder()
                        .title("Databases")
                        .content("Relational models")
                        .createdDate(LocalDate.of(2025, 8, 8))
                        .build()
        );

        registrationRepository.save(
                Registration.builder()
                        .member(member)
                        .lecture(lecture)
                        .registeredDate(LocalDate.of(2025, 8, 8))
                        .build()
        );

        // when
        List<Registration> registrations = registrationRepository.findByLecture_LectureId(lecture.getLectureId());

        // then
        Assertions.assertThat(registrations).hasSize(1);
        Assertions.assertThat(registrations.get(0).getMember().getName()).isEqualTo("Suai");
    }
}
