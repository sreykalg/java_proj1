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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final MemberService memberService;
    private final TeamService teamService;

    @PostMapping("/add")
    public CreateMemberResponse register(@RequestBody @Valid CreateMemberRequest request) {
        Team team = teamService.findById(request.teamId());
        Member member = Member.builder()
                .name(request.name())
                .age(request.age())
                .address(request.address())
                .createdDate(request.createdDate())
                .team(team)
                .build();
        Long id = memberService.register(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/{id}/edit")
    public UpdateMemberResponse update(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.name(), request.age(), request.address());
        Member updated = memberService.findById(id);
        return new UpdateMemberResponse(updated.getMemberId(), updated.getName());
    }

    @GetMapping("/list")
    public Result<List<GetMemberResponse>> list() {
        List<GetMemberResponse> members = memberService.findMembers().stream()
                .map(m -> new GetMemberResponse(m.getMemberId(), m.getName(), m.getAge(), m.getAddress(), m.getCreatedDate()))
                .collect(Collectors.toList());
        return new Result<>(members.size(), members);
    }

    @GetMapping("/{id}/lectures")
    public Result<List<String>> lectures(@PathVariable("id") Long id) {
        Member member = memberService.findById(id);
        List<String> lectureTitles = member.getRegistrations().stream()
                .map(reg -> reg.getLecture().getTitle())
                .collect(Collectors.toList());
        return new Result<>(lectureTitles.size(), lectureTitles);
    }

    public record CreateMemberRequest(
            @NotEmpty String name,
            int age,
            @NotEmpty String address,
            @JsonFormat(pattern = "yyyy-MM-dd")
            @Valid LocalDate createdDate,
            Long teamId
    ) {}

    public record CreateMemberResponse(Long id) {}

    public record UpdateMemberRequest(
            @NotEmpty String name,
            int age,
            @NotEmpty String address
    ) {}

    public record UpdateMemberResponse(Long id, String name) {}

    public record GetMemberResponse(Long id, String name, int age, String address, LocalDate createdDate) {}

    public record Result<T>(int count, T data) {}
}
