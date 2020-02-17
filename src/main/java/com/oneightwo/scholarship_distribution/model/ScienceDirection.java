package com.oneightwo.scholarship_distribution.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigInteger;

//@Data
@NoArgsConstructor
@Entity
@Table(name = "science_directions")
public class ScienceDirection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @NonNull
    private String name;

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

    //    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "scienceDirection")
//    private List<Student> students;
}
