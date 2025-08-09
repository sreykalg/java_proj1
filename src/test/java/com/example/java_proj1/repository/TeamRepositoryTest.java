package com.example.java_proj1.repository;

import com.example.java_proj1.domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Transactional
class TeamRepositoryTest {

    @Autowired
    TeamRepository teamRepository;

    @Test
    void saveAndFindById(){
        // given
        LocalDate date1 = LocalDate.parse("2025-08-08");
        Team team = Team.builder()
                .name("TeamUser")
                .createdDate(date1)
                .build();

        // when
        Team savedTeam = teamRepository.save(team);
        Long teamId = savedTeam.getTeamId();

        // then
        Optional<Team> findTeam = teamRepository.findById(teamId);
        Assertions.assertThat(findTeam).isPresent();
        Assertions.assertThat(findTeam.get().getName()).isEqualTo("TeamUser");
    }
}
