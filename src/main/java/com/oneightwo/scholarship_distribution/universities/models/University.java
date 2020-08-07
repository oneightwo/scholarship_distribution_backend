package com.oneightwo.scholarship_distribution.universities.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String abbreviation;
    @Column(nullable = false)
    private boolean deleted;
}
