package com.project.codeinterviews.DTO;

import java.util.List;

public class AdminQuestionDTO {
    private List<QuestionDetail> questions;

    public AdminQuestionDTO(List<QuestionDetail> questions) {
        this.questions = questions;
    }

    public List<QuestionDetail> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDetail> questions) {
        this.questions = questions;
    }

    public static class QuestionDetail {
        private Long id;
        private String title;
        private String typology;
        private int difficulty;

        public QuestionDetail(Long id, String title, String typology, int difficulty) {
            this.id = id;
            this.title = title;
            this.typology = typology;
            this.difficulty = difficulty;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTypology() {
            return typology;
        }

        public void setTypology(String typology) {
            this.typology = typology;
        }

        public int getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }
    }
}
