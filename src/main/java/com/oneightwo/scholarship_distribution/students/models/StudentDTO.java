package com.oneightwo.scholarship_distribution.students.models;

import com.oneightwo.scholarship_distribution.courses.models.Course;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import com.oneightwo.scholarship_distribution.universities.models.University;
import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StudentDTO implements Comparable<StudentDTO> {
    @JsonProperty("student_id")
    private Long id;

    @NotNull
    private String surname;

    @NotNull
    private String name;

    private String patronymic;

    @JsonProperty("university_id")
    private Long universityId;

    @NotNull
    private String faculty;

    @NotNull
    @JsonProperty("course_id")
    private Long courseId;

    @NotNull
    private String email;

    private String phone;

    @NotNull
    @JsonProperty("science_direction_id")
    private Long scienceDirectionId;

    @NotNull
    private String topic;

    @NotNull
    private int c1;

    @NotNull
    private int c2;

    @NotNull
    private int c3;

    @NotNull
    private int c4;

    @NotNull
    private int c5;

    @NotNull
    private int c6;

    @NotNull
    private int c7;

    @NotNull
    private int c8;

    @NotNull
    private int c9;

    @NotNull
    private int c10;

    @NotNull
    private int c11;

    @NotNull
    private int c12;

    @NotNull
    private int c13;

    @NotNull
    private int c14;

    @NotNull
    private int c15;

    private int rating;

    @NotNull
    @JsonProperty("is_valid")
    private boolean isValid;

    public StudentDTO(@NotNull String surname, @NotNull String name, String patronymic, Long universityId, @NotNull String faculty, @NotNull Long courseId, @NotNull String email, String phone, @NotNull Long scienceDirectionId, @NotNull String topic, @NotNull int c1, @NotNull int c2, @NotNull int c3, @NotNull int c4, @NotNull int c5, @NotNull int c6, @NotNull int c7, @NotNull int c8, @NotNull int c9, @NotNull int c10, @NotNull int c11, @NotNull int c12, @NotNull int c13, @NotNull int c14, @NotNull int c15, @NotNull boolean isValid) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.universityId = universityId;
        this.faculty = faculty;
        this.courseId = courseId;
        this.email = email;
        this.phone = phone;
        this.scienceDirectionId = scienceDirectionId;
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

    @Override
    public int compareTo(StudentDTO o) {
        return this.getRating() - o.getRating();
    }
}

