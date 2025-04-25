package com.project.codeinterviews.DTO;

public class DomandaDTO {
    private long id;
    private String title;
    private int difficulty;
    private String typology;

    public DomandaDTO() {

    }

    public DomandaDTO(long id, String title, int difficulty, String typology) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.typology = typology;
    }

    public DomandaDTO(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }
}
