package com.oneightwo.scholarship_distribution.courses.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CourseDTO implements Comparable<CourseDTO> {

    private Long id;
    private Integer value;

    @Override
    public int compareTo(CourseDTO o) {
        return this.value.compareTo(o.value);
    }
}
