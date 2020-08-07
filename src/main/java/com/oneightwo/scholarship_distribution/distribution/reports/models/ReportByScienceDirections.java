package com.oneightwo.scholarship_distribution.distribution.reports.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.TreeMap;

@AllArgsConstructor
@Getter
public class ReportByScienceDirections {

    private final TreeMap<Long, Integer> numberApplications;

    private final TreeMap<Long, Double> averageRatings;

    private final TreeMap<Long, Double> minimalRating;

    private final TreeMap<Long, Integer> numberPassedApplications;

    private final TreeMap<Long, Integer> numberExcludedApplications;

    private final TreeMap<Long, Integer> assignedNumberScholarships;

}
