package com.project.codeinterviews.DTO;

import java.time.LocalDateTime;

import com.project.codeinterviews.entity.SessioneCandidato;

public class SessioneCandidatoDetailsDTO {
    private String email;
    private SessioneCandidato.Status status;
    private String token;
    private long id;
    private LocalDateTime expirationDate;
    private LocalDateTime creationDate;

    public SessioneCandidatoDetailsDTO(String email, SessioneCandidato.Status status, long id, String token, LocalDateTime expirationDate, LocalDateTime creationDate) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.status = status;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;

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

    public String getToken() {
        return token;
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
