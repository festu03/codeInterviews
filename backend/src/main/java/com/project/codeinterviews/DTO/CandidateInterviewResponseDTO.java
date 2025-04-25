package com.project.codeinterviews.DTO;

public class CandidateInterviewResponseDTO {
    private String title;
    private String typology;
    private int difficulty;
    private String description;
    private String answer;

    public CandidateInterviewResponseDTO(String title, String typology, int difficulty, String description, String answer) {
        this.title = title;
        this.typology = typology;
        this.difficulty = difficulty;
        this.description = description;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }
}
