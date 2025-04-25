package com.project.codeinterviews.controller;


import com.project.codeinterviews.DTO.*;
import com.project.codeinterviews.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/questions")
public class AdminQuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewQuestionResponseDTO createQuestion(@RequestBody NewQuestionDTO newQuestionDTO) {
        return questionService.createQuestion(newQuestionDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AdminQuestionDTO.QuestionDetail> getQuestions(Pageable pageable) {
        return questionService.getAdminQuestions(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getQuestionById(@PathVariable Long id) {
        var question = questionService.getQuestionById(id);

        if (question == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(question);
    }


}
