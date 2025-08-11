package com.example.java_proj1.api.controller;

import com.example.java_proj1.service.RegistrationService;
import com.example.java_proj1.service.dto.RegistrationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registrations")
public class RegistrationApiController {

    private final RegistrationService registrationService;

    @PostMapping("/add")
    public CreateRegistrationResponse register(@RequestBody @Valid RegistrationDTO dto) {
        RegistrationDTO saved = registrationService.register(dto);
        return new CreateRegistrationResponse(saved.getRegistrationId());
    }

    @GetMapping("/list")
    public Result<List<RegistrationDTO>> list() {
        List<RegistrationDTO> list = registrationService.findAll();
        return new Result<>(list.size(), list);
    }

    public record CreateRegistrationResponse(Long id) {}

    public record Result<T>(int count, T data) {}
}
