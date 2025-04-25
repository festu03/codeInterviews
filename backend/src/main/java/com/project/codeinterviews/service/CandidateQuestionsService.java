package com.project.codeinterviews.service;

import com.project.codeinterviews.DTO.AnswerDTO;
import com.project.codeinterviews.entity.Domanda;
import com.project.codeinterviews.entity.GuestUserPrincipal;
import com.project.codeinterviews.entity.Risposta;
import com.project.codeinterviews.entity.SessioneCandidato;
import com.project.codeinterviews.repository.DomandaRepository;
import com.project.codeinterviews.repository.RispostaRepository;
import com.project.codeinterviews.repository.SessioneCandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CandidateQuestionsService {
    @Autowired
    private RispostaRepository rispostaRepository;
    @Autowired
    private SessioneCandidatoRepository sessioneCandidatoRepository;
    @Autowired
    private DomandaRepository domandaRepository;

    public ResponseEntity<?> saveAnswer(long id, AnswerDTO answerBody, GuestUserPrincipal principal) {
        String token = principal.getToken();
        SessioneCandidato sessioneCandidato = sessioneCandidatoRepository.findByToken(token);
        Domanda domanda = domandaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (answerBody == null || answerBody.toString().isEmpty() || rispostaRepository.existsBySessioneCandidatoAndDomanda(sessioneCandidato, domanda)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Risposta risposta = new Risposta();
            risposta.setSessioneCandidato(sessioneCandidato);
            risposta.setDomanda(domanda);
            risposta.setRisposta(answerBody.getAnswerBody());
            rispostaRepository.save(risposta);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
