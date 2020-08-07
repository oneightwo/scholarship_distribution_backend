package com.oneightwo.scholarship_distribution.distribution.reports.services.impl;

import com.oneightwo.scholarship_distribution.core.helpers.TransformationHelper;
import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportByUniversities;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByUniversitiesService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsAndUniversitiesReportService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
public class ReportByUniversitiesServiceImpl implements ReportByUniversitiesService {

    private final StudentService studentService;
    private final ScholarshipDistributionByDirectionsAndUniversitiesReportService directionsAndUniversitiesReportService;
    private final ScienceDirectionService scienceDirectionService;
    private final UniversityService universityService;

    public ReportByUniversitiesServiceImpl(StudentService studentService,
                                           ScienceDirectionService scienceDirectionService,
                                           UniversityService universityService,
                                           ScholarshipDistributionByDirectionsAndUniversitiesReportService directionsAndUniversitiesReportService) {
        this.studentService = studentService;
        this.scienceDirectionService = scienceDirectionService;
        this.universityService = universityService;
        this.directionsAndUniversitiesReportService = directionsAndUniversitiesReportService;
    }

    @Override
    public ReportByUniversities execute(Semester semester, int year) {
        directionsAndUniversitiesReportService.setStudents(studentService.getStudentsBySemesterAndYear(semester, year));
        return new ReportByUniversities(
                directionsAndUniversitiesReportService.getAllApplicationsSubmittedToUniversities(),
                directionsAndUniversitiesReportService.getPassedApplicationsSubmittedToUniversities(),
                directionsAndUniversitiesReportService.getExcludedApplicationsSubmittedToUniversities(),
                directionsAndUniversitiesReportService.getDataDistributionByUniversitiesAndDirections(),
                directionsAndUniversitiesReportService.getDataDistributionByUniversitiesInDirections(),
                new TreeSet<>(TransformationHelper.scienceDirectionsToDtos(scienceDirectionService.getExisting())),
                new TreeSet<>(TransformationHelper.universitiesToDtos(universityService.getExisting()))
        );
    }
}
