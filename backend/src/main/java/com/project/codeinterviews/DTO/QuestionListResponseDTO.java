package com.project.codeinterviews.DTO;


import java.util.List;

public class QuestionListResponseDTO {

    private List<DomandaDTO> questions;

    public QuestionListResponseDTO(List<DomandaDTO> questions) {
        this.questions = questions;
    }

    public List<DomandaDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<DomandaDTO> questions) {
        this.questions = questions;
    }
}

