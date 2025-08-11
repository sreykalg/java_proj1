package com.example.java_proj1.service.mapper;

import com.example.java_proj1.domain.Member;
import com.example.java_proj1.service.dto.MemberDTO;
import com.example.java_proj1.service.dto.TeamDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDTO toDto(Member member) {
        if (member == null) return null;

        TeamDTO teamDTO = null;
        if (member.getTeam() != null) {
            teamDTO = TeamDTO.builder()
                    .teamId(member.getTeam().getTeamId())
                    .name(member.getTeam().getName())
                    .createdDate(member.getTeam().getCreatedDate())
                    .build();
        }

        return MemberDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .address(member.getAddress())
                .createdDate(member.getCreatedDate())
                .teamId(member.getTeam() != null ? member.getTeam().getTeamId() : null)
                .team(teamDTO)
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