package com.oneightwo.scholarship_distribution.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private boolean activeRegistration;

    public Settings() {
    }

    public Settings(boolean activeRegistration) {
        this.activeRegistration = activeRegistration;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public boolean isActiveRegistration() {
        return activeRegistration;
    }

    public void setActiveRegistration(boolean activeRegistration) {
        this.activeRegistration = activeRegistration;
    }
}
