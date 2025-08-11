package com.example.java_proj1.service;

import com.example.java_proj1.domain.Team;
import com.example.java_proj1.repository.MemberRepository;
import com.example.java_proj1.repository.TeamRepository;
import com.example.java_proj1.service.dto.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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

        MemberDTO dto = MemberDTO.builder()
                .name("Alice")
                .age(22)
                .address("Street-199")
                .createdDate(LocalDate.now())
                .teamId(team.getTeamId())
                .build();

        MemberDTO savedDto = memberService.register(dto);
        MemberDTO foundDto = memberService.findById(savedDto.getMemberId());

        Assertions.assertThat(foundDto.getName()).isEqualTo("Alice");
        Assertions.assertThat(foundDto.getTeamId()).isEqualTo(team.getTeamId());
    }

    @Test
    void updateMemberInfo() {
        Team team = teamRepository.save(Team.builder()
                .name("Test Team")
                .createdDate(LocalDate.now())
                .build());

        MemberDTO dto = MemberDTO.builder()
                .name("Bob")
                .age(20)
                .address("Street-200")
                .createdDate(LocalDate.now())
                .teamId(team.getTeamId())
                .build();

        MemberDTO savedDto = memberService.register(dto);

        MemberDTO updateDto = MemberDTO.builder()
                .name("Bob Updated")
                .age(25)
                .address("Street-100")
                .teamId(team.getTeamId())
                .build();

        MemberDTO updatedDto = memberService.update(savedDto.getMemberId(), updateDto);

        Assertions.assertThat(updatedDto.getName()).isEqualTo("Bob Updated");
        Assertions.assertThat(updatedDto.getAge()).isEqualTo(25);
        Assertions.assertThat(updatedDto.getAddress()).isEqualTo("Street-100");
    }
}
