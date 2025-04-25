package com.project.codeinterviews.service;

import com.project.codeinterviews.entity.GuestUserPrincipal;
import com.project.codeinterviews.entity.SessioneCandidato;
import com.project.codeinterviews.repository.SessioneCandidatoRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestUserPrincipalService {
    @Autowired
    SessioneCandidatoRepository sessioneCandidatoRepository;

    public GuestUserPrincipal findValidToken(String token) throws AuthenticationException {
        SessioneCandidato sessioneCandidato = sessioneCandidatoRepository.findByToken(token);
        if (sessioneCandidato != null && sessioneCandidato.getStatus().equals(SessioneCandidato.Status.VALID)) {
            return new GuestUserPrincipal(sessioneCandidato.getEmail(), token);
        }
        throw new AuthenticationException("Token not found or invalid");
    }
}
