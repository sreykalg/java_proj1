package com.example.java_proj1.api.controller;

import com.example.java_proj1.service.TeamService;
import com.example.java_proj1.service.dto.TeamDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamApiController {

    private final TeamService teamService;

    @PostMapping("/add")
    public CreateTeamResponse register(@RequestBody @Valid TeamDTO dto) {
        Long id = teamService.register(dto).getTeamId();
        return new CreateTeamResponse(id);
    }

    @PutMapping("/{id}/edit")
    public UpdateTeamResponse update(@PathVariable Long id, @RequestBody @Valid TeamDTO dto) {
        teamService.update(id, dto);
        return new UpdateTeamResponse(id, dto.getName());
    }

    @GetMapping("/list")
    public List<TeamDTO> list() {
        return teamService.findAll();
    }

    public record CreateTeamResponse(Long id) {}
    public record UpdateTeamResponse(Long id, String name) {}
}

