package com.example.java_proj1.controller;

import com.example.java_proj1.service.TeamService;
import com.example.java_proj1.service.dto.TeamDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/list")
    public String listTeams(Model model) {
        List<TeamDTO> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "teams/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("teamDTO", TeamDTO.builder()
                .teamId(null)
                .name("")
                .createdDate(LocalDate.now())
                .build());
        return "teams/add";
    }

    // @ModelAttribute can be omitted.
    @PostMapping("/add")
    public String addTeam(@Valid @ModelAttribute("teamDTO") TeamDTO dto,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teams/add";
        }
        teamService.register(dto);
        return "redirect:/teams/list";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        TeamDTO dto = teamService.findById(id);
        model.addAttribute("teamDTO", dto);
        return "teams/edit";
    }

    // @ModelAttribute can be omitted.
    @PostMapping("/{id}/edit")
    public String editTeam(@PathVariable Long id,
                           @Valid @ModelAttribute("teamDTO") TeamDTO dto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "teams/edit";
        }
        teamService.update(id, dto);
        return "redirect:/teams/list";
    }
}

