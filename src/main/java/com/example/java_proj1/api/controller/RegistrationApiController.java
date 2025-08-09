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
@RequestMapping("/api/v1/registrations")
class RegistrationApiController {

    private final RegistrationService registrationService;
    private final MemberService memberService;
    private final LectureService lectureService;

    @PostMapping("/add")
    public CreateRegistrationResponse register(@RequestBody @Valid CreateRegistrationRequest request) {
        Member member = memberService.findById(request.memberId());
        Lecture lecture = lectureService.findById(request.lectureId());

        Registration registration = Registration.builder()
                .registeredDate(request.registeredDate())
                .build();
        Long id = registrationService.register(registration);
        return new CreateRegistrationResponse(id);
    }

    @GetMapping("/list")
    public Result<List<Registration>> list() {
        List<Registration> registrations = registrationService.findAll();
        return new Result<>(registrations.size(), registrations);
    }

    public record CreateRegistrationRequest(
            @JsonFormat(pattern = "yyyy-MM-dd")
            @Valid LocalDate registeredDate,
            Long memberId,
            Long lectureId
    ) {}

    public record CreateRegistrationResponse(Long id) {}

    public record Result<T>(int count, T data) {}
}