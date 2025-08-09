package com.example.java_proj1.controller;

import com.example.java_proj1.api.controller.MemberApiController.CreateMemberRequest;
import com.example.java_proj1.api.controller.MemberApiController.UpdateMemberRequest;
import com.example.java_proj1.api.controller.MemberApiController.GetMemberResponse;
import com.example.java_proj1.domain.Member;
import com.example.java_proj1.domain.Team;
import com.example.java_proj1.service.MemberService;
import com.example.java_proj1.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TeamService teamService;

    // List all members - return view
    @GetMapping("/list")
    public String listMembers(Model model) {
        List<Member> members = memberService.findMembers();
        // Map to GetMemberResponse DTOs (same as your API)
        List<GetMemberResponse> memberResponses = members.stream()
                .map(m -> new GetMemberResponse(m.getMemberId(), m.getName(), m.getAge(), m.getAddress(), m.getCreatedDate()))
                .collect(Collectors.toList());
        model.addAttribute("members", memberResponses);
        return "members/list";  // thymeleaf template: templates/members/list.html
    }

    // Show Add Member form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("createMemberRequest", new CreateMemberRequest("", 0, "", LocalDate.now(), null));
        model.addAttribute("teams", teamService.findAll());
        return "members/add";  // templates/members/add.html
    }

    // Handle Add Member form submit
    @PostMapping("/add")
    public String addMember(@Valid @ModelAttribute("createMemberRequest") CreateMemberRequest request,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamService.findAll());
            return "members/add";
        }
        Team team = null;
        if (request.teamId() != null) {
            team = teamService.findById(request.teamId());
        }
        Member member = Member.builder()
                .name(request.name())
                .age(request.age())
                .address(request.address())
                .createdDate(request.createdDate())
                .team(team)
                .build();
        memberService.register(member);
        return "redirect:/members/list";
    }

    // Show Edit Member form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Member member = memberService.findById(id);
        UpdateMemberRequest updateRequest = new UpdateMemberRequest(member.getName(), member.getAge(), member.getAddress());
        model.addAttribute("updateMemberRequest", updateRequest);
        model.addAttribute("memberId", id);
        model.addAttribute("teams", teamService.findAll());
        return "members/edit";  // templates/members/edit.html
    }

    // Handle Edit Member form submit
    @PostMapping("/{id}/edit")
    public String editMember(@PathVariable("id") Long id,
                             @Valid @ModelAttribute("updateMemberRequest") UpdateMemberRequest request,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("memberId", id);
            model.addAttribute("teams", teamService.findAll());
            return "members/edit";
        }
        memberService.update(id, request.name(), request.age(), request.address());
        return "redirect:/members/list";
    }
}
