package com.oneightwo.scholarship_distribution.distribution.reports.services;

import com.oneightwo.scholarship_distribution.data_view.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportByWinningStudents;

public interface ReportByWinningStudentsService {

    ReportByWinningStudents execute(Semester semester, int year);
}
