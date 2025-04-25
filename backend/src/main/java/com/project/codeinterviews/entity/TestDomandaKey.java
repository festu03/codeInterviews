package com.project.codeinterviews.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TestDomandaKey implements Serializable {

    @Column(name = "TEST_ID")
    private long testId;

    @Column(name = "DOMANDA_ID")
    private long domandaId;

    public TestDomandaKey() {
    }

    public TestDomandaKey(long domandaId, long testId) {
        this.domandaId = domandaId;
        this.testId = testId;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public long getDomandaId() {
        return domandaId;
    }

    public void setDomandaId(long domandaId) {
        this.domandaId = domandaId;
    }
}
