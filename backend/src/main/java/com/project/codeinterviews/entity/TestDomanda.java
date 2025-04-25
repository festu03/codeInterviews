package com.project.codeinterviews.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TEST_DOMANDA")
public class TestDomanda {

    @EmbeddedId
    private TestDomandaKey id;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "TEST_ID")
    private Test test;

    @ManyToOne
    @MapsId("domandaId")
    @JoinColumn(name = "DOMANDA_ID")
    private Domanda domanda;

    @Column(name = "ORDINE", nullable = false)
    private int ordine;

    public TestDomanda() {

    }

    public TestDomanda(TestDomandaKey id, Test test, Domanda domanda, int ordine) {
        this.id = id;
        this.test = test;
        this.domanda = domanda;
        this.ordine = ordine;
    }

    public TestDomandaKey getId() {
        return id;
    }

    public void setId(TestDomandaKey id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Domanda getDomanda() {
        return domanda;
    }

    public void setDomanda(Domanda domanda) {
        this.domanda = domanda;
    }

    public int getOrdine() {
        return ordine;
    }

    public void setOrdine(int ordine) {
        this.ordine = ordine;
    }
}
