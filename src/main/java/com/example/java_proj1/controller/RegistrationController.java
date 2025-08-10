package com.example.java_proj1.controller;


import com.example.java_proj1.api.controller.RegistrationApiController;
import com.example.java_proj1.domain.Lecture;
import com.example.java_proj1.domain.Member;
import com.example.java_proj1.domain.Registration;
import com.example.java_proj1.service.LectureService;
import com.example.java_proj1.service.MemberService;
import com.example.java_proj1.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final MemberService memberService;
    private final LectureService lectureService;

    //List all registrations page
    @GetMapping("/list")
    public String listRegistrations(Model model) {
        List<Registration> registrations = registrationService.findAll();
        model.addAttribute("registrations", registrations);
        return "registrations/list";
    }

    //Show add registration form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("createRegistrationRequest", new RegistrationApiController.CreateRegistrationRequest(LocalDate.now(), null, null));
        model.addAttribute("members", memberService.findMembers());
        model.addAttribute("lectures", lectureService.findAll());
        return "registrations/add";
    }

    //add registration form submit
    @PostMapping("/add")
    public String addRegistration(@Valid @ModelAttribute("createRegistrationRequest") RegistrationApiController.CreateRegistrationRequest request,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("members", memberService.findMembers());
            model.addAttribute("lectures", lectureService.findAll());
            return "registrations/add";
        }

        Member member = null;
        Lecture lecture = null;
        if (request.memberId() != null) {
            member = memberService.findById(request.memberId());
        }
        if (request.lectureId() != null) {
            lecture = lectureService.findById(request.lectureId());
        }

        Registration registration = Registration.builder()
                .registeredDate(request.registeredDate())
                .member(member)
                .lecture(lecture)
                .build();

        registrationService.register(registration);
        return "redirect:/registrations/list";
    }
}
