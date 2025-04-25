package com.project.codeinterviews.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "DOMANDA")
public class Domanda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "TITOLO", nullable = false)
    private String titolo;

    @Column(name = "TIPOLOGIA", nullable = false)
    private String tipologia;

    @Column(name = "DIFFICOLTA", nullable = false)
    private int difficolta;

    @Column(name = "DESCRIZIONE", nullable = false, length = 500)
    private String descrizione;

    @OneToMany(mappedBy = "domanda", cascade = CascadeType.ALL)
    private List<TestDomanda> testDomande;

    public Domanda() {
    }

    public Domanda(long id, String titolo, String tipologia, int difficolta, String descrizione, List<TestDomanda> testDomande) {
        this.id = id;
        this.titolo = titolo;
        this.tipologia = tipologia;
        this.difficolta = difficolta;
        this.descrizione = descrizione;
        this.testDomande = testDomande;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<TestDomanda> getTestDomande() {
        return testDomande;
    }

    public void setTestDomande(List<TestDomanda> testDomande) {
        this.testDomande = testDomande;
    }
}
