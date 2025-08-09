package com.example.java_proj1.service;

import com.example.java_proj1.domain.Team;
import com.example.java_proj1.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Long register(Team team){
        teamRepository.save(team);
        return team.getTeamId();
    }

    @Transactional
    public void update(Long teamId, String name){
        Team team = teamRepository.findById(teamId).orElseThrow();
        team.setName(name);
    }

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Team findById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found with id: " + teamId));
    }

}