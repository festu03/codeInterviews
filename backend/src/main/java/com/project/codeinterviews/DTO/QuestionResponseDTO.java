package com.project.codeinterviews.DTO;

public class QuestionResponseDTO {
    private String titolo;
    private String descrizione;
    private String tipologia;
    private int difficolta;

    public QuestionResponseDTO(String titolo, String descrizione, String tipologia, int difficolta) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.difficolta = difficolta;
    }

    public QuestionResponseDTO() {

    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(int difficolta) {
        this.difficolta = difficolta;
    }
}
