package com.example.java_proj1.controller;

import com.example.java_proj1.service.LectureService;
import com.example.java_proj1.service.MemberService;
import com.example.java_proj1.service.RegistrationService;
import com.example.java_proj1.service.dto.RegistrationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final MemberService memberService;
    private final LectureService lectureService;

    @GetMapping("/list")
    public String listRegistrations(Model model) {
        List<RegistrationDTO> registrations = registrationService.findAll();
        model.addAttribute("registrations", registrations);
        return "registrations/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        // RegistrationDTO dto = new RegistrationDTO();
        RegistrationDTO dto = RegistrationDTO.builder()
                .registrationId(null)
                .memberId(null)
                .lectureId(null)
                .registeredDate(LocalDate.now())
                .build();

        model.addAttribute("registrationDTO", dto);
        model.addAttribute("members", memberService.findMembers());
        model.addAttribute("lectures", lectureService.findAll());
        return "registrations/add";
    }


    @PostMapping("/add")
    public String addRegistration(@Valid @ModelAttribute("registrationDTO") RegistrationDTO dto,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            // Wow! I was going to talk about this later, but you did it.
            model.addAttribute("members", memberService.findMembers());  // <-- fixed here
            model.addAttribute("lectures", lectureService.findAll());
            return "registrations/add";
        }
        registrationService.register(dto);
        return "redirect:/registrations/list";
    }

    @GetMapping("/registered_members")
    public String listRegisteredMembers(Model model) {
        List<RegistrationDTO> registrations = registrationService.findAll();
        model.addAttribute("registrations", registrations);
        return "registrations/registered_members"; // new html file
    }


}
