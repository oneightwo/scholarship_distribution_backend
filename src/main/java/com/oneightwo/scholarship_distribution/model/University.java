package com.oneightwo.scholarship_distribution.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigInteger;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @NonNull
    private String name;
    @NonNull
    private String abbreviation;

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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    //    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "university")
//    private List<Student> students;
}
