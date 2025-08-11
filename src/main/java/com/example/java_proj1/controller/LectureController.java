package com.example.java_proj1.controller;

import com.example.java_proj1.service.LectureService;
import com.example.java_proj1.service.dto.LectureDTO;
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
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/list")
    public String listLectures(Model model) {
        List<LectureDTO> lectures = lectureService.findAll();
        model.addAttribute("lectures", lectures);
        return "lectures/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("lectureDTO", LectureDTO.builder()
                .lectureId(null)
                .title("")
                .content("")
                .createdDate(LocalDate.now())
                .build()
        );
        return "lectures/add";
    }

    @PostMapping("/add")
    public String addLecture(@Valid @ModelAttribute("lectureDTO") LectureDTO lectureDTO,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "lectures/add";
        }
        lectureService.register(lectureDTO);
        return "redirect:/lectures/list";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        LectureDTO lectureDTO = lectureService.findById(id);
        model.addAttribute("lectureDTO", lectureDTO);
        return "lectures/edit";
    }

    @PostMapping("/{id}/edit")
    public String editLecture(@PathVariable Long id,
                              @Valid @ModelAttribute("lectureDTO") LectureDTO lectureDTO,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "lectures/edit";
        }
        lectureService.update(id, lectureDTO);
        return "redirect:/lectures/list";
    }
}
