package com.oneightwo.scholarship_distribution.distribution.reports.models;

import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirectionDTO;
import com.oneightwo.scholarship_distribution.universities.models.UniversityDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ReportByUniversities {

    private final TreeSet<ReportUnit<Long, Integer>> allApplicationsSubmittedToUniversities;

    private final TreeSet<ReportUnit<Long, Integer>> passedApplicationsSubmittedToUniversities;

    private final TreeSet<ReportUnit<Long, Integer>> excludedApplicationsSubmittedToUniversities;

    private final TreeSet<ReportUnit<Long, TreeSet<ReportUnit<Long, Integer>>>> dataDistributionByUniversitiesAndDirections;

    private final TreeSet<ReportUnit<Long, TreeSet<ReportUnit<Long, UniversityReport>>>> dataDistributionByUniversitiesInDirections;

    private final TreeSet<ScienceDirectionDTO> scienceDirections;

    private final TreeSet<UniversityDTO> universities;

}
