package com.oneightwo.scholarship_distribution.distribution.reports.models;

import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirectionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

@AllArgsConstructor
@Getter
@ToString
public class ReportByScienceDirections {

    private final TreeSet<ReportUnit<Long, Integer>> numberApplications;

    private final TreeSet<ReportUnit<Long, Double>> averageRatings;

    private final TreeSet<ReportUnit<Long, Double>> minimalRating;

    private final TreeSet<ReportUnit<Long, Integer>> numberPassedApplications;

    private final TreeSet<ReportUnit<Long, Integer>> numberExcludedApplications;

    private final TreeSet<ReportUnit<Long, Integer>> assignedNumberScholarships;

    private final List<ScienceDirectionDTO> scienceDirections;

}
