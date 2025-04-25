package com.project.codeinterviews.DTO;

import java.util.List;

public class NewInterviewDTO {

    private String interviewTitle;
    private List<QuestionOrderDTO> questions;
    private String email;

    public String getInterviewTitle() {
        return interviewTitle;
    }

    public void setInterviewTitle(String interviewTitle) {
        this.interviewTitle = interviewTitle;
    }

    public List<QuestionOrderDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionOrderDTO> questions) {
        this.questions = questions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class QuestionOrderDTO {
        private long id;
        private int order;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }

}
