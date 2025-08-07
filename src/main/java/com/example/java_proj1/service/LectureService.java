package com.example.java_proj1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository repo;
    private final RegistrationRepository regRepo;
    private final LectureMapper mapper;
    private final MemberMapper memberMapper;

    public LectureDTO addLecture(LectureDTO dto) {
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public LectureDTO updateLecture(Long id, LectureDTO dto) {
        Lecture l = repo.findById(id).orElseThrow();
        l.setTitle(dto.getTitle());
        return mapper.toDto(repo.save(l));
    }

    public List<LectureDTO> list() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    public List<MemberDTO> students(Long lectureId) {
        return regRepo.findByLecture_LectureId(lectureId).stream()
                .map(r -> memberMapper.toDto(r.getMember())).toList();
    }
}



