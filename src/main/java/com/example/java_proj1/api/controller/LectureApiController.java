package com.example.java_proj1.api.controller;

import com.example.java_proj1.service.LectureService;
import com.example.java_proj1.service.dto.LectureDTO;
import com.example.java_proj1.service.dto.MemberDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
public class LectureApiController {

    private final LectureService lectureService;

    @PostMapping("/add")
    public CreateLectureResponse register(@RequestBody @Valid LectureDTO lectureDTO) {
        Long id = lectureService.register(lectureDTO).getLectureId();
        return new CreateLectureResponse(id);
    }

    @PutMapping("/{id}/edit")
    public UpdateLectureResponse update(@PathVariable Long id, @RequestBody @Valid LectureDTO lectureDTO) {
        lectureService.update(id, lectureDTO);
        return new UpdateLectureResponse(id, lectureDTO.getTitle());
    }

    @GetMapping("/list")
    public List<LectureDTO> list() {
        return lectureService.findAll();
    }

    public record CreateLectureResponse(Long id) {}
    public record UpdateLectureResponse(Long id, String title) {}
}


