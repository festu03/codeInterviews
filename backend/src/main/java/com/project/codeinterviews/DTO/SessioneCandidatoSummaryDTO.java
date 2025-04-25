package com.project.codeinterviews.DTO;

import com.project.codeinterviews.entity.SessioneCandidato;

public class SessioneCandidatoSummaryDTO {
    private String email;
    private SessioneCandidato.Status status;
    private long id;

    public SessioneCandidatoSummaryDTO(String email, SessioneCandidato.Status status, long id) {
        this.email = email;
        this.status = status;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
