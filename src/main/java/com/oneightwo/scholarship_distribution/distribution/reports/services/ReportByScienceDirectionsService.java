package com.oneightwo.scholarship_distribution.distribution.reports.services;

import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportByScienceDirections;

public interface ReportByScienceDirectionsService {

    ReportByScienceDirections execute(Semester semester, int year);
}
