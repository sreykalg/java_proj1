package com.example.java_proj1.service;

import com.example.java_proj1.domain.Member;
import com.example.java_proj1.domain.Team;
import com.example.java_proj1.repository.MemberRepository;
import com.example.java_proj1.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void registerAndFindMember() {
        Team team = teamRepository.save(Team.builder()
                .name("Test Team")
                .createdDate(LocalDate.now())
                .build());

        Member member = Member.builder()
                .name("Alice")
                .age(22)
                .address("Street-199")
                .createdDate(LocalDate.now())
                .team(team)
                .build();

        Long memberId = memberService.register(member);
        Member found = memberService.findById(memberId);

        Assertions.assertThat(found.getName()).isEqualTo("Alice");
    }

    @Test
    void updateMemberInfo() {
        Team team = teamRepository.save(Team.builder()
                .name("Test Team")
                .createdDate(LocalDate.now())
                .build());

        Member member = memberRepository.save(Member.builder()
                .name("Bob")
                .age(20)
                .address("Stree-200")
                .createdDate(LocalDate.now())
                .team(team)
                .build());

        memberService.update(member.getMemberId(), "Bob Updated", 25, "Street-100");

        Member updated = memberRepository.findById(member.getMemberId()).orElseThrow();
        Assertions.assertThat(updated.getName()).isEqualTo("Bob Updated");
        Assertions.assertThat(updated.getAge()).isEqualTo(25);
        Assertions.assertThat(updated.getAddress()).isEqualTo("Street-100");
    }
}
