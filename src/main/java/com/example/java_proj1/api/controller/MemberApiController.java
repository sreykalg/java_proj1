package com.example.java_proj1.api.controller;

import com.example.java_proj1.service.MemberService;
import com.example.java_proj1.service.dto.MemberDTO;
import com.example.java_proj1.service.dto.LectureDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/add")
    public CreateMemberResponse register(@RequestBody @Valid MemberDTO dto) {
        Long id = memberService.register(dto).getMemberId();
        return new CreateMemberResponse(id);
    }

    @PutMapping("/{id}/edit")
    public UpdateMemberResponse update(@PathVariable Long id, @RequestBody @Valid MemberDTO dto) {
        memberService.update(id, dto);
        return new UpdateMemberResponse(id, dto.getName());
    }

    @GetMapping("/list")
    public List<MemberDTO> list() {
        return memberService.findMembers();
    }

    public record CreateMemberResponse(Long id) {}
    public record UpdateMemberResponse(Long id, String name) {}
}

