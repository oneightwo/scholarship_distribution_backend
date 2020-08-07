package com.oneightwo.scholarship_distribution.distribution.reports.services;

import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportByUniversities;

public interface ReportByUniversitiesService {

    ReportByUniversities execute(Semester semester, int year);
}
