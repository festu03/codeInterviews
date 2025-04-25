package com.project.codeinterviews.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEST")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "TITOLO", nullable = false)
    private String title;

    @Column(name = "DIFFICOLTA", nullable = false)
    private int difficulty;

    @OneToOne(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private SessioneCandidato sessioneCandidato;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestDomanda> testDomande = new ArrayList<>();

    public Test() {
    }

    public Test(long id, String title, int difficulty, SessioneCandidato sessioneCandidato, List<TestDomanda> testDomande) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.sessioneCandidato = sessioneCandidato;
        this.testDomande = testDomande;
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

    public SessioneCandidato getSessioneCandidato() {
        return sessioneCandidato;
    }

    public void setSessioneCandidato(SessioneCandidato sessioneCandidato) {
        this.sessioneCandidato = sessioneCandidato;
    }

    public List<TestDomanda> getTestDomande() {
        return testDomande;
    }

    public void setTestDomande(TestDomanda testDomande) {
        this.testDomande.add(testDomande);
    }
}
