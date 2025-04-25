package com.project.codeinterviews.controller;

import com.project.codeinterviews.DTO.AnswerDTO;
import com.project.codeinterviews.entity.Domanda;
import com.project.codeinterviews.entity.GuestUserPrincipal;
import com.project.codeinterviews.service.CandidateQuestionsService;
import com.project.codeinterviews.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidate/questions")
public class CandidateQuestionsController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CandidateQuestionsService candidateQuestionsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        List<Domanda> domande = questionService.getQuestionForCandidate(id, token);

        if (domande.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Map<String, Object>> response = domande.stream().map(d -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", d.getId());
            map.put("titolo", d.getTitolo());
            map.put("descrizione", d.getDescrizione());
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/answer")
    public ResponseEntity<?> saveAnswer(@PathVariable long id, @RequestBody AnswerDTO answerBody, @AuthenticationPrincipal GuestUserPrincipal principal) {
        return candidateQuestionsService.saveAnswer(id, answerBody, principal);
    }

    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
