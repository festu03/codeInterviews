package com.project.codeinterviews.repository;

import com.project.codeinterviews.entity.Domanda;
import com.project.codeinterviews.entity.Risposta;
import com.project.codeinterviews.entity.SessioneCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RispostaRepository extends JpaRepository<Risposta, Long> {
    boolean existsBySessioneCandidatoAndDomanda(SessioneCandidato sessioneCandidato, Domanda domanda);

    Risposta findBySessioneCandidatoAndDomanda(SessioneCandidato sessioneCandidato, Domanda domanda);
}
