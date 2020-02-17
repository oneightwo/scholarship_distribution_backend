package com.oneightwo.scholarship_distribution.security.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String name;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<User> userList;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
