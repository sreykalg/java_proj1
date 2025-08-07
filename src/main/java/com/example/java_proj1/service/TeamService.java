package com.example.java_proj1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository repo;
    private final TeamMapper mapper;

    // add
    public TeamDTO addTeam(TeamDTO dto) {
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    // update
    public TeamDTO updateTeam(Long id, TeamDTO dto) {
        Team t = repo.findById(id).orElseThrow();
        t.setName(dto.getName());
        return repo.finaAll().stream().map(mapper::toDto).toList();
    }

    // list
    public List<TeamDTO> list() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

}
