package com.project.codeinterviews.DTO;

import java.util.ArrayList;
import java.util.List;

public class InterviewDetailsDTO {
    private Long id;
    private String title;
    private List<DomandaDTO> questions = new ArrayList<>();

    public InterviewDetailsDTO(String title, List<DomandaDTO> questions) {
        this.title = title;
        this.questions = questions;
    }

    public InterviewDetailsDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DomandaDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<DomandaDTO> questions) {
        this.questions = questions;
    }
}
