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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
class LectureApiController {

    private final LectureService lectureService;

    @PostMapping("/add")
    public CreateLectureResponse register(@RequestBody @Valid CreateLectureRequest request) {
        Lecture lecture = Lecture.builder()
                .title(request.title())
                .content(request.content())
                .createdDate(request.createdDate())
                .build();
        Long id = lectureService.register(lecture);
        return new CreateLectureResponse(id);
    }

    @PutMapping("/{id}/edit")
    public UpdateLectureResponse update(@PathVariable("id") Long id, @RequestBody @Valid UpdateLectureRequest request) {
        lectureService.update(id, request.title(), request.content());
        return new UpdateLectureResponse(id, request.title());
    }

    @GetMapping("/list")
    public Result<List<Lecture>> list() {
        List<Lecture> lectures = lectureService.findAll();
        return new Result<>(lectures.size(), lectures);
    }

    public record CreateLectureRequest(
            @NotEmpty String title,
            @NotEmpty String content,
            @JsonFormat(pattern = "yyyy-MM-dd")
            @Valid LocalDate createdDate
    ) {}

    public record CreateLectureResponse(Long id) {}

    public record UpdateLectureRequest(@NotEmpty String title, @NotEmpty String content) {}

    public record UpdateLectureResponse(Long id, String title) {}

    public record Result<T>(int count, T data) {}
}