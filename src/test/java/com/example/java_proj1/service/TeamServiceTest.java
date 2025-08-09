package com.example.java_proj1.service;

import com.example.java_proj1.domain.Team;
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
class TeamServiceTest {

    @Autowired
    TeamService teamService;

    @Autowired
    TeamRepository teamRepository;

    @Test
    void registerAndFindAll() {
        // given
        Team team = Team.builder()
                .name("Team Alpha")
                .createdDate(LocalDate.of(2025, 8, 8))
                .build();

        // when
        Long id = teamService.register(team);
        List<Team> teams = teamService.findAll();

        // then
        Assertions.assertThat(teams).extracting("name").contains("Team Alpha");
    }

    @Test
    void updateTeamName() {
        // given
        Team team = teamRepository.save(Team.builder()
                .name("Original Name")
                .createdDate(LocalDate.now())
                .build());

        // when
        teamService.update(team.getTeamId(), "Updated Name");

        // then
        Team updated = teamRepository.findById(team.getTeamId()).orElseThrow();
        Assertions.assertThat(updated.getName()).isEqualTo("Updated Name");
    }
}
