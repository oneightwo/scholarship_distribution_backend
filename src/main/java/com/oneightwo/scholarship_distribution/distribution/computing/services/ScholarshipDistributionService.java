package com.oneightwo.scholarship_distribution.distribution.computing.services;

import com.oneightwo.scholarship_distribution.distribution.constants.Semester;

import java.util.Map;

public interface ScholarshipDistributionService {

    Map<Long, Map<Long, Integer>> execute(Semester semester, int year);
}
