package com.project.codeinterviews.repository;

import com.project.codeinterviews.entity.Domanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomandaRepository extends JpaRepository<Domanda, Long> {

    @Query("SELECT d FROM Domanda d " + "JOIN TestDomanda td ON d.id = td.domanda.id " + "JOIN SessioneCandidato sc ON td.test.id = sc.test.id " + "WHERE d.id = :idDomanda AND sc.token = :token")
    List<Domanda> findDomandaByIdAndToken(@Param("idDomanda") Long idDomanda, @Param("token") String token);
}