package com.example.java_proj1.service;

import com.example.java_proj1.domain.Member;
import com.example.java_proj1.domain.Team;
import com.example.java_proj1.repository.MemberRepository;
import com.example.java_proj1.repository.TeamRepository;
import com.example.java_proj1.service.dto.MemberDTO;
import com.example.java_proj1.service.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public MemberDTO register(MemberDTO dto) {
        validateDuplicateMember(dto.getName());

        Member member = memberMapper.toEntity(dto);

        // Set team if provided
        if (dto.getTeamId() != null) {
            Team team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new IllegalArgumentException("Team not found: " + dto.getTeamId()));
            member.changeTeam(team);
        }

        memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    private void validateDuplicateMember(String name) {
        List<Member> byName = memberRepository.findByName(name);
        if (!byName.isEmpty()) {
            throw new IllegalStateException("This member already exists.");
        }
    }


    public List<MemberDTO> findMembers() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toDto)
                .toList();
    }

    public MemberDTO findById(Long memberId) {
        return memberRepository.findById(memberId)
                .map(memberMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
    }

    @Transactional
    public MemberDTO update(Long memberId, MemberDTO dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        member.changeName(dto.getName());
        member.changeAge(dto.getAge());
        member.changeAddress(dto.getAddress());

        if (dto.getCreatedDate() != null) {
            member.setCreatedDate(dto.getCreatedDate()); // or member.changeCreatedDate(...)
        }

        if (dto.getTeamId() != null) {
            Team team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new IllegalArgumentException("Team not found: " + dto.getTeamId()));
            member.changeTeam(team);
        }

        return memberMapper.toDto(member);
    }

}
