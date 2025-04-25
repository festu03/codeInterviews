package com.project.codeinterviews.service;

import com.project.codeinterviews.DTO.SessioneCandidatoDetailsDTO;
import com.project.codeinterviews.DTO.SessioneCandidatoSummaryDTO;
import com.project.codeinterviews.entity.SessioneCandidato;
import com.project.codeinterviews.repository.SessioneCandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessioneCandidatoService {
    @Autowired
    SessioneCandidatoRepository sessioneCandidatoRepository;

    public List<SessioneCandidatoSummaryDTO> getCandidates() {
        List<SessioneCandidato> candidates = (List<SessioneCandidato>) sessioneCandidatoRepository.findAll();
        List<SessioneCandidatoSummaryDTO> sessioneCandidatoSummaryDTOList = new ArrayList<>();
        for (SessioneCandidato candidate : candidates) {
            SessioneCandidatoSummaryDTO sessioneCandidatoSummaryDTO = new SessioneCandidatoSummaryDTO(
                    candidate.getEmail(), candidate.getStatus(), candidate.getId());
            sessioneCandidatoSummaryDTOList.add(sessioneCandidatoSummaryDTO);
        }
        return sessioneCandidatoSummaryDTOList;
    }

    public SessioneCandidatoDetailsDTO getCandidateById(long id) {
        SessioneCandidato sessioneCandidato = sessioneCandidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id not found"));

        SessioneCandidatoDetailsDTO sessioneCandidatoDetailsDTO = new SessioneCandidatoDetailsDTO(
                sessioneCandidato.getEmail(),
                sessioneCandidato.getStatus(),
                sessioneCandidato.getId(),
                sessioneCandidato.getToken(),
                sessioneCandidato.getExpirationDate(),
                sessioneCandidato.getCreationDate());
        return sessioneCandidatoDetailsDTO;
    }

    public void deleteCandidate(long id) {
        sessioneCandidatoRepository.deleteById(id);
    }

    public void updateCandidateTokenStatus(long id, SessioneCandidato.Status status) {
        SessioneCandidato sessioneCandidato = sessioneCandidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id not found"));
        sessioneCandidato.setStatus(status);
        sessioneCandidatoRepository.save(sessioneCandidato);
    }
}
