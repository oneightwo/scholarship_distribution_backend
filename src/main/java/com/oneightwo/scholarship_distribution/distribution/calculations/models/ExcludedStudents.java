package com.oneightwo.scholarship_distribution.distribution.calculations.models;

import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;

public final class ExcludedStudents extends AbstractStudentInDistributionAndUniversity {

    public ExcludedStudents(List<Student> excludedStudentList) {
        super.addStudents(excludedStudentList);
    }

}
