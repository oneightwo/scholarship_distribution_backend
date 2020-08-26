package com.oneightwo.scholarship_distribution.distribution.calculations.models;

import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;

public final class PassedStudents extends AbstractStudentInDistributionAndUniversity {

    public PassedStudents(List<Student> students) {
        addStudents(students);
    }

    @Override
    protected void addStudent(Student student) {
        if (checkIsValidStudent(student))
            super.addStudent(student);
    }

    @Override
    protected void addStudents(List<Student> students) {
        students.forEach(student -> {
            if (checkIsValidStudent(student))
                super.addStudent(student);
        });
    }

    private boolean checkIsValidStudent(Student student) {
        return student.getIsValid();
    }

}
