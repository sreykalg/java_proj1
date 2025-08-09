package com.example.java_proj1.repository;

import com.example.java_proj1.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void savedAndFindById() {
        // given
        LocalDate date0 = LocalDate.parse("2025-09-08");
        Member member = new Member(null, "testUser", 10, "Street-AB", date0);

        // when
        memberJpaRepository.save(member);
        Long saveId = member.getMemberId();

        // then
        Member findMember = memberJpaRepository.findById(saveId);
        Assertions.assertThat(findMember).isEqualTo(member);
        Assertions.assertThat(findMember.getName()).isEqualTo("testUser");
    }

    @Test
    void findByName() {
        LocalDate date1 = LocalDate.parse("2025-08-08");
        LocalDate date2 = LocalDate.parse("2025-08-10");
        LocalDate date3 = LocalDate.parse("2025-08-28");
        // given

        memberRepository.save(new Member(null, "Amber", 10, "Street-001", date1));
        memberRepository.save(new Member(null, "Alice", 11, "Street-002", date2));
        memberRepository.save(new Member(null, "Angkra", 12, "Street-003", date3));
        // when
        List<Member> result = memberRepository.findByName("Alice");

        // then
        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result).extracting("name").containsOnly("Alice");
    }
}
