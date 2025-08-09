package com.example.java_proj1.service.mapper;

import com.example.java_proj1.domain.Team;
import com.example.java_proj1.service.dto.TeamDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TeamMapper {

    public TeamDTO toDto(Team team) {
        if (team == null) return null;

        return TeamDTO.builder()
                .teamId(team.getTeamId())
                .name(team.getName())
                .createdDate(team.getCreatedDate())
                .build();
    }

    public Team toEntity(TeamDTO dto) {
        if (dto == null) return null;

        return Team.builder()
                .name(dto.getName())
                .createdDate(dto.getCreatedDate())
                .build();
    }
}
