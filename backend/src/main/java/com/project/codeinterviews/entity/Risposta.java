package com.project.codeinterviews.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RISPOSTA")
public class Risposta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "RISPOSTA", length = 1000, nullable = false)
    private String risposta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DOMANDA_ID", nullable = false)
    private Domanda domanda;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SESSIONE_CANDIDATO_ID", nullable = false)
    private SessioneCandidato sessioneCandidato;

    public Risposta() {

    }

    public Risposta(String risposta, SessioneCandidato sessioneCandidato, Domanda domanda) {
        this.risposta = risposta;
        this.sessioneCandidato = sessioneCandidato;
        this.domanda = domanda;
    }

    public Domanda getDomanda() {
        return domanda;
    }

    public void setDomanda(Domanda domanda) {
        this.domanda = domanda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public SessioneCandidato getSessioneCandidato() {
        return sessioneCandidato;
    }

    public void setSessioneCandidato(SessioneCandidato sessioneCandidato) {
        this.sessioneCandidato = sessioneCandidato;
    }
}
