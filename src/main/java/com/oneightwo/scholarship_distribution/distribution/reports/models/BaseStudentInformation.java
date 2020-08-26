package com.oneightwo.scholarship_distribution.distribution.reports.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class BaseStudentInformation implements Comparable<BaseStudentInformation> {

    private final String fullName;
    private final String faculty;
    private final int course;
    private final String topic;
    private final Integer rating;

    @Override
    public int compareTo(@NotNull BaseStudentInformation o) {
        return this.fullName.compareTo(o.fullName);
    }
}
