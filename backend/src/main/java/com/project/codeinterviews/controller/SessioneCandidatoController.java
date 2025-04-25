package com.project.codeinterviews.controller;

import com.project.codeinterviews.DTO.SessioneCandidatoDetailsDTO;
import com.project.codeinterviews.DTO.SessioneCandidatoSummaryDTO;
import com.project.codeinterviews.entity.SessioneCandidato;
import com.project.codeinterviews.service.SessioneCandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sessione-candidato")
public class SessioneCandidatoController {
    @Autowired
    SessioneCandidatoService sessioneCandidatoService;

    @GetMapping
    public List<SessioneCandidatoSummaryDTO> getCandidates() {
        return sessioneCandidatoService.getCandidates();
    }

    @GetMapping("/{id}")
    public SessioneCandidatoDetailsDTO getCandidateById(@PathVariable long id) {
        return sessioneCandidatoService.getCandidateById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable long id) {
        sessioneCandidatoService.deleteCandidate(id);
    }

    @PutMapping("/{id}")
    public void updateCandidateTokenStatus(@PathVariable long id, @RequestParam SessioneCandidato.Status status) {
        sessioneCandidatoService.updateCandidateTokenStatus(id, status);
    }

}
