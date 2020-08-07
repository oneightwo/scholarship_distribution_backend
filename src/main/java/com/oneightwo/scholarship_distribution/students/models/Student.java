package com.oneightwo.scholarship_distribution.students.models;

import com.oneightwo.scholarship_distribution.courses.models.Course;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import com.oneightwo.scholarship_distribution.universities.models.University;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;
    private String patronymic;
    @JoinColumn(name = "university_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    private University university;
    @Column(nullable = false)
    private String faculty;
    @Enumerated(EnumType.ORDINAL)
    private Course course;
    private String email;
    private String phone;
    @JoinColumn(name = "science_direction_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    private ScienceDirection scienceDirection;
    @Column(nullable = false)
    private String topic;
    @Column(nullable = false)
    private int c1;
    @Column(nullable = false)
    private int c2;
    @Column(nullable = false)
    private int c3;
    @Column(nullable = false)
    private int c4;
    @Column(nullable = false)
    private int c5;
    @Column(nullable = false)
    private int c6;
    @Column(nullable = false)
    private int c7;
    @Column(nullable = false)
    private int c8;
    @Column(nullable = false)
    private int c9;
    @Column(nullable = false)
    private int c10;
    @Column(nullable = false)
    private int c11;
    @Column(nullable = false)
    private int c12;
    @Column(nullable = false)
    private int c13;
    @Column(nullable = false)
    private int c14;
    @Column(nullable = false)
    private int c15;
    @Column(nullable = false)
    private int rating;
    @Column(name = "data_registration", nullable = false)
    private LocalDateTime dataRegistration;
    @Column(name = "is_valid", nullable = false)
    private boolean isValid;
}
