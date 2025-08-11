package com.example.java_proj1.service;

import com.example.java_proj1.domain.Team;
import com.example.java_proj1.repository.TeamRepository;
import com.example.java_proj1.service.dto.TeamDTO;
import com.example.java_proj1.service.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Transactional
    public TeamDTO register(TeamDTO dto) {
        Team team = teamMapper.toEntity(dto);
        teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    @Transactional
    public TeamDTO update(Long teamId, TeamDTO dto) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found: " + teamId));
        team.setName(dto.getName());
        return teamMapper.toDto(team);
    }

    public List<TeamDTO> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toDto)
                .toList();
    }

    public TeamDTO findById(Long teamId) {
        return teamRepository.findById(teamId)
                .map(teamMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Team not found: " + teamId));
    }
}
