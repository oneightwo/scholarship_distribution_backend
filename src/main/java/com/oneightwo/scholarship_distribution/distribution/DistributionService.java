package com.oneightwo.scholarship_distribution.distribution;

import com.oneightwo.scholarship_distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.model.Student;

import java.util.List;
import java.util.Map;

public interface DistributionService {

    int getRating(int[] criteria);

    Map<String, Map<String, Long>> getCountScholarshipsByDirectionAndUniversities(Semester semester, int year);

    Map<String, List<Student>> getWinnerStudents(Semester semester, int year);

    Map<String, List<Student>> getLoserStudents(Semester semester, int year);

    List<Map<String, String>> getReportByDirection(Semester semester, int year);

    Map<String, List<Map<String, String>>> getReportByDirectionAndUniversities(Semester semester, int year);


}
