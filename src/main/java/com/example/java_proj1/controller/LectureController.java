package com.example.java_proj1.controller;

import com.example.java_proj1.api.controller.LectureApiController.CreateLectureRequest;
import com.example.java_proj1.api.controller.LectureApiController.UpdateLectureRequest;
import com.example.java_proj1.domain.Lecture;
import com.example.java_proj1.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    // List all lectures page
    @GetMapping("/list")
    public String listLectures(Model model) {
        List<Lecture> lectures = lectureService.findAll();
        model.addAttribute("lectures", lectures);
        return "lectures/list";  // templates/lectures/list.html
    }

    // Show add lecture form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("createLectureRequest", new CreateLectureRequest("", "", LocalDate.now()));
        return "lectures/add";  // templates/lectures/add.html
    }

    // Handle add lecture form submit
    @PostMapping("/add")
    public String addLecture(@Valid @ModelAttribute("createLectureRequest") CreateLectureRequest request,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "lectures/add";
        }
        Lecture lecture = Lecture.builder()
                .title(request.title())
                .content(request.content())
                .createdDate(request.createdDate())
                .build();
        lectureService.register(lecture);
        return "redirect:/lectures/list";
    }

    // Show edit lecture form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Lecture lecture = lectureService.findById(id);
        UpdateLectureRequest updateRequest = new UpdateLectureRequest(lecture.getTitle(), lecture.getContent());
        model.addAttribute("updateLectureRequest", updateRequest);
        model.addAttribute("lectureId", id);
        return "lectures/edit";  // templates/lectures/edit.html
    }

    // Handle edit lecture form submit
    @PostMapping("/{id}/edit")
    public String editLecture(@PathVariable("id") Long id,
                              @Valid @ModelAttribute("updateLectureRequest") UpdateLectureRequest request,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("lectureId", id);
            return "lectures/edit";
        }
        lectureService.update(id, request.title(), request.content());
        return "redirect:/lectures/list";
    }
}
