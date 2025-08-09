package com.example.java_proj1.controller;


import com.example.java_proj1.api.controller.TeamApiController;
import com.example.java_proj1.domain.Team;
import com.example.java_proj1.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // List all teams page
    @GetMapping("/list")
    public String listTeams(Model model) {
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "teams/list";  // maps to templates/teams/list.html
    }

    // Show add team form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("createTeamRequest", new TeamApiController.CreateTeamRequest("", LocalDate.now()));
        return "teams/add";  // templates/teams/add.html
    }

    // Handle add team form submit
    @PostMapping("/add")
    public String addTeam(@Valid @ModelAttribute("createTeamRequest") TeamApiController.CreateTeamRequest request,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teams/add";
        }
        Team team = Team.builder()
                .name(request.name())
                .createdDate(request.createdDate())
                .build();
        teamService.register(team);
        return "redirect:/teams/list";
    }

    // Show edit team form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Team team = teamService.findById(id);
        TeamApiController.UpdateTeamRequest updateRequest = new TeamApiController.UpdateTeamRequest(team.getName());
        model.addAttribute("updateTeamRequest", updateRequest);
        model.addAttribute("teamId", id);
        return "teams/edit";  // templates/teams/edit.html
    }

    // Handle edit team form submit
    @PostMapping("/{id}/edit")
    public String editTeam(@PathVariable("id") Long id,
                           @Valid @ModelAttribute("updateTeamRequest") TeamApiController.UpdateTeamRequest request,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teamId", id);
            return "teams/edit";
        }
        teamService.update(id, request.name());
        return "redirect:/teams/list";
    }
}
