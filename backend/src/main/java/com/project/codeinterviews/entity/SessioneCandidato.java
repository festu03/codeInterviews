package com.project.codeinterviews.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SESSIONE_CANDIDATO")
public class SessioneCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    public enum Status {
        VALID, REVOKED, EXPIRED
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    @Column(name = "TOKEN", unique = true, nullable = false)
    private String token;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "EXPIRATION_DATE", nullable = false)
    private LocalDateTime expirationDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TEST_ID", referencedColumnName = "id")
    private Test test;

    @OneToMany(mappedBy = "sessioneCandidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Risposta> risposte;


    public SessioneCandidato() {

    }

    public SessioneCandidato(String email, String token, LocalDateTime creationDate, LocalDateTime expirationDate, Status status) {
        this.email = email;
        this.token = token;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SessioneCandidato.Status getStatus() {
        return status;
    }

    public void setStatus(SessioneCandidato.Status status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
