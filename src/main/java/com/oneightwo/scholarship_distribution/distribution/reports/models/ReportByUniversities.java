package com.oneightwo.scholarship_distribution.distribution.reports.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.TreeMap;

@AllArgsConstructor
@Getter
public class ReportByUniversities {

    TreeMap<Long, Integer> allApplicationsSubmittedToUniversities;

    TreeMap<Long, Integer> passedApplicationsSubmittedToUniversities;

    TreeMap<Long, Integer> excludedApplicationsSubmittedToUniversities;

    TreeMap<Long, TreeMap<Long, Integer>> dataDistributionByUniversitiesAndDirections;

    TreeMap<Long, TreeMap<Long, UniversityReport>> dataDistributionByUniversitiesInDirections;


}
