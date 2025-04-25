package com.project.codeinterviews.repository;

import com.project.codeinterviews.entity.Domanda;
import com.project.codeinterviews.entity.Test;
import com.project.codeinterviews.entity.TestDomanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDomandaRepository extends JpaRepository<TestDomanda, Long> {
    boolean existsByDomandaAndTest(Domanda domanda, Test test);
}
