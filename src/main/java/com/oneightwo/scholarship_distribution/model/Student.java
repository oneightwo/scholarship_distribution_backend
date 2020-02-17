package com.oneightwo.scholarship_distribution.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

//@Data
@ToString
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;
    private String patronymic;
    @JoinColumn(name = "university_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private University university;
    @Column(nullable = false)
    private String faculty;
    @JoinColumn(name = "course_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Course course;
    private String email;
    private String phone;
    @JoinColumn(name = "science_direction_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
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
    private int rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataRegistration;
    @Column(nullable = false)
    private boolean isValid;

    public Student() {
    }

    public Student(String surname, String name, String patronymic, University university, String faculty, Course course, String email, String phone, ScienceDirection scienceDirection, String topic, int c1, int c2, int c3, int c4, int c5, int c6, int c7, int c8, int c9, int c10, int c11, int c12, int c13, int c14, int c15, boolean isValid) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.university = university;
        this.faculty = faculty;
        this.course = course;
        this.email = email;
        this.phone = phone;
        this.scienceDirection = scienceDirection;
        this.topic = topic;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
        this.c7 = c7;
        this.c8 = c8;
        this.c9 = c9;
        this.c10 = c10;
        this.c11 = c11;
        this.c12 = c12;
        this.c13 = c13;
        this.c14 = c14;
        this.c15 = c15;
        this.isValid = isValid;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ScienceDirection getScienceDirection() {
        return scienceDirection;
    }

    public void setScienceDirection(ScienceDirection scienceDirection) {
        this.scienceDirection = scienceDirection;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public int getC3() {
        return c3;
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public int getC4() {
        return c4;
    }

    public void setC4(int c4) {
        this.c4 = c4;
    }

    public int getC5() {
        return c5;
    }

    public void setC5(int c5) {
        this.c5 = c5;
    }

    public int getC6() {
        return c6;
    }

    public void setC6(int c6) {
        this.c6 = c6;
    }

    public int getC7() {
        return c7;
    }

    public void setC7(int c7) {
        this.c7 = c7;
    }

    public int getC8() {
        return c8;
    }

    public void setC8(int c8) {
        this.c8 = c8;
    }

    public int getC9() {
        return c9;
    }

    public void setC9(int c9) {
        this.c9 = c9;
    }

    public int getC10() {
        return c10;
    }

    public void setC10(int c10) {
        this.c10 = c10;
    }

    public int getC11() {
        return c11;
    }

    public void setC11(int c11) {
        this.c11 = c11;
    }

    public int getC12() {
        return c12;
    }

    public void setC12(int c12) {
        this.c12 = c12;
    }

    public int getC13() {
        return c13;
    }

    public void setC13(int c13) {
        this.c13 = c13;
    }

    public int getC14() {
        return c14;
    }

    public void setC14(int c14) {
        this.c14 = c14;
    }

    public int getC15() {
        return c15;
    }

    public void setC15(int c15) {
        this.c15 = c15;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getDataRegistration() {
        return dataRegistration;
    }

    public void setDataRegistration(LocalDateTime dataRegistration) {
        this.dataRegistration = dataRegistration;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
