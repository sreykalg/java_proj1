package com.example.java_proj1.controller;

import com.example.java_proj1.service.MemberService;
import com.example.java_proj1.service.TeamService;
import com.example.java_proj1.service.dto.MemberDTO;
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
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping("/list")
    public String listMembers(Model model) {
        List<MemberDTO> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        // I recommended
        // MemberDTO dto = new MemberDTO();
        model.addAttribute("memberDTO", MemberDTO.builder()
                .memberId(null)
                .name("")
                .age(0)
                .address("")
                .createdDate(LocalDate.now())
                .teamId(null)
                .build()
        );
        model.addAttribute("teams", teamService.findAll());
        return "members/add";
    }

    @PostMapping("/add")
    public String addMember(@Valid @ModelAttribute("memberDTO") MemberDTO dto,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            return "members/add";
        }

        try {
            memberService.register(dto);
        } catch (IllegalStateException e) {
            bindingResult.rejectValue("name", "duplicate", e.getMessage());
            return "members/add";
        }

        return "redirect:/members/list";
    }


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        MemberDTO dto = memberService.findById(id);
        model.addAttribute("memberDTO", dto);
        model.addAttribute("teams", teamService.findAll());
        return "members/edit";
    }

    @PostMapping("/{id}/edit")
    public String editMember(@PathVariable Long id, @Valid @ModelAttribute("memberDTO") MemberDTO dto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamService.findAll());
            return "members/edit";
        }
        memberService.update(id, dto);
        return "redirect:/members/list";
    }
}
