package com.example.java_proj1.api.controller;

import com.example.java_proj1.domain.*;
import com.example.java_proj1.service.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
class TeamApiController {

    private final TeamService teamService;

    @PostMapping("/add")
    public CreateTeamResponse register(@RequestBody @Valid CreateTeamRequest request) {
        Team team = Team.builder()
                .name(request.name())
                .createdDate(request.createdDate())
                .build();
        Long id = teamService.register(team);
        return new CreateTeamResponse(id);
    }

    @PutMapping("/{id}/edit")
    public UpdateTeamResponse update(@PathVariable("id") Long id, @RequestBody @Valid UpdateTeamRequest request) {
        teamService.update(id, request.name());
        return new UpdateTeamResponse(id, request.name());
    }

    @GetMapping("/list")
    public Result<List<Team>> list() {
        List<Team> teams = teamService.findAll();
        return new Result<>(teams.size(), teams);
    }

    public record CreateTeamRequest(
            @NotEmpty String name,
            @JsonFormat(pattern = "yyyy-MM-dd")
            @Valid LocalDate createdDate
    ) {}

    public record CreateTeamResponse(Long id) {}

    public record UpdateTeamRequest(@NotEmpty String name) {}

    public record UpdateTeamResponse(Long id, String name) {}

    public record Result<T>(int count, T data) {}
}
