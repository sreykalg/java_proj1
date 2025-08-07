package com.example.java_proj1.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepo;
    private final TeamRepository teamRepo;
    private final RegistrationRepository registrationRepo;
    private final MemberMapper memberMapper;

    public MemberDTO addMember(MemberDTO dto) {
        Member member = memberMapper.toEntity(dto);
        member.setTeam(teamRepo.findById(dto.getTeamId()).orElseThrow());
        return memberMapper.toDto(memberRepo.save(member));
    }

    public MemberDTO updateMember(Long id, MemberDTO dto) {
        Member member = memberRepo.findById(id).orElseThrow();
        member.setName(dto.getName());
        member.setTeam(teamRepo.findById(dto.getTeamId()).orElseThrow());
        return memberMapper.toDto(memberRepo.save(member));
    }

    public List<MemberDTO> getAllMembers() {
        return memberRepo.findAll().stream()
                .map(memberMapper::toDto).toList();
    }

    public List<LectureDTO> getLecturesByMember(Long memberId) {
        return registrationRepo.findByMember_MemberId(memberId).stream()
                .map(r -> r.getLecture())
                .map(l -> new LectureDTO(l.getLectureId(), l.getTitle()))
                .toList();
    }
}


