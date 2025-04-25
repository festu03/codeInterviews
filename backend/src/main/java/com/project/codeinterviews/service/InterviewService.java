package com.project.codeinterviews.service;

import com.project.codeinterviews.DTO.*;
import com.project.codeinterviews.entity.*;
import com.project.codeinterviews.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InterviewService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private SessioneCandidatoRepository sessioneCandidatoRepository;
    @Autowired
    private DomandaRepository domandaRepository;
    @Autowired
    private TestDomandaRepository testDomandaRepository;
    @Autowired
    private RispostaRepository rispostaRepository;


    public NewInterviewResponseDTO createInterview(NewInterviewDTO interviewDTO) {
        Test newTest = new Test();
        newTest.setTitle(interviewDTO.getInterviewTitle());

        String email = interviewDTO.getEmail();
        String token = UUID.randomUUID().toString();
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime expirationDate = creationDate.plusDays(7);
        SessioneCandidato.Status status = SessioneCandidato.Status.VALID;
        SessioneCandidato sessioneCandidato = new SessioneCandidato(email, token, creationDate, expirationDate, status);

        newTest.setSessioneCandidato(sessioneCandidato);
        sessioneCandidato.setTest(newTest);
        double summaryDifficulty = 0;
        int count = 0;
        for (NewInterviewDTO.QuestionOrderDTO questionOrderDTO : interviewDTO.getQuestions()) {
            Domanda domanda = domandaRepository.findById(questionOrderDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            summaryDifficulty += domanda.getDifficolta();
            count++;
            TestDomandaKey id = new TestDomandaKey((domanda.getId()), newTest.getId());
            TestDomanda testDomanda = new TestDomanda(id, newTest, domanda, questionOrderDTO.getOrder());
            newTest.setTestDomande(testDomanda);
        }
        double mediumDifficulty = summaryDifficulty / count;
        newTest.setDifficulty((int) Math.round(mediumDifficulty));
        testRepository.save(newTest);

        NewInterviewResponseDTO response = new NewInterviewResponseDTO();
        response.setCandidateToken(token);
        return response;
    }

    public Page<InterviewSummaryDTO> getInterviews(Pageable pageable) {
        Page<Test> tests = testRepository.findAll(pageable);
        return tests.map(test -> new InterviewSummaryDTO(
                test.getId(),
                test.getTitle(),
                test.getDifficulty(),
                test.getSessioneCandidato().getEmail()

        ));
    }


    public ResponseEntity<InterviewDetailsDTO> getInterviewById(Long interviewId) {
        Test test = testRepository.findById(interviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<DomandaDTO> questions = new ArrayList<>();

        for (TestDomanda testDomanda : test.getTestDomande()) {
            Domanda domanda = testDomanda.getDomanda();
            DomandaDTO domandaDTO = new DomandaDTO(domanda.getId(), domanda.getTitolo(), domanda.getDifficolta(), domanda.getTipologia());
            questions.add(domandaDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new InterviewDetailsDTO(test.getTitle(), questions));
    }

    public ResponseEntity<?> getAssignedInterview(GuestUserPrincipal principal) {
        String token = principal.getToken();
        SessioneCandidato sessioneCandidato = sessioneCandidatoRepository.findByToken(token);
        //se il candidato non esiste o non ha un test assegnato ritorna un 404
        if (sessioneCandidato == null || sessioneCandidato.getTest() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Test test = sessioneCandidato.getTest();
        List<DomandaDTO> questions = new ArrayList<>();

        for (TestDomanda testDomanda : test.getTestDomande()) {
            Domanda domanda = testDomanda.getDomanda();
            DomandaDTO domandaDTO = new DomandaDTO(
                    domanda.getId(),
                    domanda.getTitolo(),
                    domanda.getDifficolta(),
                    domanda.getTipologia()
            );
            questions.add(domandaDTO);
        }
        // restituisce ok con il test e le domande
        return ResponseEntity.status(HttpStatus.OK).body(new InterviewDetailsDTO(test.getTitle(), questions));
    }

    public ResponseEntity<?> getInterviewAnswer(Long interviewId, Long questionId) {
        Domanda domanda = domandaRepository.findById(questionId)
                .orElse(null);
        Test test = testRepository.findById(interviewId)
                .orElse(null);
        SessioneCandidato sessioneCandidato = test.getSessioneCandidato();
        boolean domandaExists = testDomandaRepository.existsByDomandaAndTest(domanda, test);
        boolean rispostaExists = rispostaRepository.existsBySessioneCandidatoAndDomanda(sessioneCandidato, domanda);

        if (domanda == null || test == null || sessioneCandidato == null || !domandaExists || !rispostaExists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Risposta risposta = rispostaRepository.findBySessioneCandidatoAndDomanda(sessioneCandidato, domanda);
            return ResponseEntity.status(HttpStatus.OK).body(new CandidateInterviewResponseDTO(domanda.getTitolo(), domanda.getTipologia(), domanda.getDifficolta(), domanda.getDescrizione(), risposta.getRisposta()));
        }
    }
}
