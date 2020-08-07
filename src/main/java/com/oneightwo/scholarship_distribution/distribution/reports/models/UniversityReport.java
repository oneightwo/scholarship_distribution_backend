package com.oneightwo.scholarship_distribution.distribution.reports.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class UniversityReport {

    private final Double averageRating;
    private final Integer numberPassedStudent;
    private final Integer numberScholarships;

}
