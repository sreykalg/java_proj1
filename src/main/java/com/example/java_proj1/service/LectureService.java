package com.example.java_proj1.service;

import com.example.java_proj1.domain.Lecture;
import com.example.java_proj1.repository.LectureRepository;
import com.example.java_proj1.service.dto.LectureDTO;
import com.example.java_proj1.service.mapper.LectureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Transactional
    public LectureDTO register(LectureDTO dto) {
        Lecture lecture = lectureMapper.toEntity(dto);
        lectureRepository.save(lecture);
        return lectureMapper.toDto(lecture);
    }

    @Transactional
    public LectureDTO update(Long lectureId, LectureDTO dto) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found: " + lectureId));

        lecture.changeTitle(dto.getTitle());
        lecture.changeContent(dto.getContent());

        return lectureMapper.toDto(lecture);
    }

    public List<LectureDTO> findAll() {
        return lectureRepository.findAll()
                .stream()
                .map(lectureMapper::toDto)
                .toList();
    }

    public LectureDTO findById(Long id) {
        return lectureRepository.findById(id)
                .map(lectureMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found: " + id));
    }
}
