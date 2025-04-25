package com.project.codeinterviews.service;

import com.project.codeinterviews.DTO.*;
import com.project.codeinterviews.entity.Domanda;
import com.project.codeinterviews.repository.DomandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {
    @Autowired
    DomandaRepository domandaRepository;

    public NewQuestionResponseDTO createQuestion(NewQuestionDTO newQuestionDTO) {
        Domanda domanda = new Domanda();
        domanda.setTitolo(newQuestionDTO.getTitle());
        domanda.setTipologia(newQuestionDTO.getTypology());
        domanda.setDifficolta(newQuestionDTO.getDifficulty());
        domanda.setDescrizione(newQuestionDTO.getDescription());
        domandaRepository.save(domanda);
        return new NewQuestionResponseDTO(domanda.getId());
    }

    public Page<AdminQuestionDTO.QuestionDetail> getAdminQuestions(Pageable pageable) {
        Page<Domanda> domandaPage = domandaRepository.findAll(pageable);

        return domandaPage.map(domanda -> new AdminQuestionDTO.QuestionDetail(
                domanda.getId(),
                domanda.getTitolo(),
                domanda.getTipologia(),
                domanda.getDifficolta()
        ));
    }

    public QuestionResponseDTO getQuestionById(Long id) {
        var domanda = domandaRepository.findById(id).orElse(null);

        if (domanda == null) {
            return null;
        }

        QuestionResponseDTO dto = new QuestionResponseDTO();
        dto.setTitolo(domanda.getTitolo());
        dto.setDescrizione(domanda.getDescrizione());
        dto.setTipologia(domanda.getTipologia());
        dto.setDifficolta(domanda.getDifficolta());
        return dto;
    }

    public List<Domanda> getQuestionForCandidate(Long id, String token) {
        return (List<Domanda>) domandaRepository.findDomandaByIdAndToken(id, token);
    }
}






