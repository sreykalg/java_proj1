package com.example.java_proj1.service;

import com.example.java_proj1.domain.Team;
import com.example.java_proj1.repository.TeamRepository;
import com.example.java_proj1.service.dto.TeamDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
class TeamServiceTest {

    @Autowired
    TeamService teamService;

    @Autowired
    TeamRepository teamRepository;

    @Test
    void registerAndFindAll() {
        // given
        TeamDTO dto = TeamDTO.builder()
                .name("Team Alpha")
                .createdDate(LocalDate.of(2025, 8, 8))
                .build();

        // when
        TeamDTO savedDto = teamService.register(dto);

        // then
        List<TeamDTO> teams = teamService.findAll();
        Assertions.assertThat(teams).extracting("name").contains("Team Alpha");
    }

    @Test
    void updateTeamName() {
        // given
        Team team = teamRepository.save(Team.builder()
                .name("Original Name")
                .createdDate(LocalDate.now())
                .build());

        TeamDTO updateDto = TeamDTO.builder()
                .name("Updated Name")
                .createdDate(team.getCreatedDate())
                .build();

        // when
        teamService.update(team.getTeamId(), updateDto);

        // then
        Team updated = teamRepository.findById(team.getTeamId()).orElseThrow();
        Assertions.assertThat(updated.getName()).isEqualTo("Updated Name");
    }
}
