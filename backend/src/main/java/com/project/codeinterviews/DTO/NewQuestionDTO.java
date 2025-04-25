package com.project.codeinterviews.DTO;

public class NewQuestionDTO {
    private String title;
    private String typology;
    private int difficulty;
    private String description;


    public NewQuestionDTO(String title, String typology, int difficulty, String description) {
        this.title = title;
        this.typology = typology;
        this.difficulty = difficulty;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
