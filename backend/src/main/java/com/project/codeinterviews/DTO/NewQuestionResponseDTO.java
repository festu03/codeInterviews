package com.project.codeinterviews.DTO;

public class NewQuestionResponseDTO {
    private long questionId;

    public NewQuestionResponseDTO(long id) {
        this.questionId = id;
    }

    public long getId() {
        return questionId;
    }

    public void setId(long id) {
        this.questionId = id;
    }
}
