package com.oneightwo.scholarship_distribution.distribution.reports.services.impl;

import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportByUniversities;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByUniversitiesService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsAndUniversitiesReportService;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import org.springframework.stereotype.Service;

@Service
public class ReportByUniversitiesServiceImpl implements ReportByUniversitiesService {

    private final StudentService studentService;
    private final ScholarshipDistributionByDirectionsAndUniversitiesReportService directionsAndUniversitiesReportService;

    public ReportByUniversitiesServiceImpl(StudentService studentService,
                                           ScholarshipDistributionByDirectionsAndUniversitiesReportService directionsAndUniversitiesReportService) {
        this.studentService = studentService;
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
                directionsAndUniversitiesReportService.getDataDistributionByUniversitiesInDirections()
        );
    }
}
