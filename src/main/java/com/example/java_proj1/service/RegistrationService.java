package com.example.java_proj1.service;

import com.example.java_proj1.domain.Registration;
import com.example.java_proj1.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    @Transactional
    public Long register(Registration registration) {
        registrationRepository.save(registration);
        return registration.getRegistrationId();
    }

    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

}
