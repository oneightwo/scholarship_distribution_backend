package com.oneightwo.scholarship_distribution.distribution.reports.services.impl;

import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportByScienceDirections;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByScienceDirectionsService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsReportService;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import org.springframework.stereotype.Service;

@Service
public class ReportByScienceDirectionsServiceImpl implements ReportByScienceDirectionsService {

    private final StudentService studentService;
    private final ScholarshipDistributionByDirectionsReportService scholarshipDistributionByDirectionsReportService;


    public ReportByScienceDirectionsServiceImpl(StudentService studentService,
                                                ScholarshipDistributionByDirectionsReportService scholarshipDistributionByDirectionsReportService) {
        this.studentService = studentService;
        this.scholarshipDistributionByDirectionsReportService = scholarshipDistributionByDirectionsReportService;
    }

    @Override
    public ReportByScienceDirections execute(Semester semester, int year) {
        scholarshipDistributionByDirectionsReportService.setStudents(studentService.getStudentsBySemesterAndYear(semester, year));
        return new ReportByScienceDirections(
                scholarshipDistributionByDirectionsReportService.getNumberApplications(),
                scholarshipDistributionByDirectionsReportService.getAverageRatings(),
                scholarshipDistributionByDirectionsReportService.getMinimalRating(),
                scholarshipDistributionByDirectionsReportService.getNumberPassedApplications(),
                scholarshipDistributionByDirectionsReportService.getNumberExcludedApplications(),
                scholarshipDistributionByDirectionsReportService.getAssignedNumberScholarships()
        );
    }
}
