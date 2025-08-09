package com.example.java_proj1.service.mapper;

import com.example.java_proj1.domain.Member;
import com.example.java_proj1.service.dto.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDTO toDto(Member member) {
        if (member == null) return null;

        return MemberDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .address(member.getAddress())
                .createdDate(member.getCreatedDate())
                .teamId(member.getTeam() != null ? member.getTeam().getTeamId() : null)
                .build();
    }

    public Member toEntity(MemberDTO dto) {
        if (dto == null) return null;

        return Member.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .address(dto.getAddress())
                .createdDate(dto.getCreatedDate())
                .build();
    }
}