package com.project.codeinterviews.repository;

import com.project.codeinterviews.entity.SessioneCandidato;
import org.springframework.data.repository.CrudRepository;

public interface SessioneCandidatoRepository extends CrudRepository<SessioneCandidato, Long> {
    SessioneCandidato findByToken(String token);
}
