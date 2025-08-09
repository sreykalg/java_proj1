package com.example.java_proj1.service;


import com.example.java_proj1.domain.Lecture;
import com.example.java_proj1.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional
    public Long register(Lecture lecture) {
        lectureRepository.save(lecture);
        return lecture.getLectureId();
    }

    @Transactional
    public void update(Long lectureId, String title, String content) {
        var lecture = lectureRepository.findById(lectureId).orElseThrow();
        lecture.changeTitle(title);
        lecture.changeContent(content);
    }

    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Lecture findById(Long id) {
        return lectureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found: " + id));
    }

}



