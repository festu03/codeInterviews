package com.project.codeinterviews.DTO;

public class InterviewSummaryDTO {
    private Long id;
    private String title;
    private int difficulty;
    private String email;

    public InterviewSummaryDTO(Long id, String title, int difficulty, String email) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.email = email;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
