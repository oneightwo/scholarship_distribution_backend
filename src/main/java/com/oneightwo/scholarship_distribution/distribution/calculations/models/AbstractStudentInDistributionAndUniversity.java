package com.oneightwo.scholarship_distribution.distribution.calculations.models;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStudentInDistributionAndUniversity {

    private final Map<Long, DistributionUnit> studentsInDirectionsAndUniversities = new HashMap<>();

    public Map<Long, DistributionUnit> getStudentsInDirectionsAndDistributionUnit() {
        return studentsInDirectionsAndUniversities;
    }

    protected void addStudents(List<Student> students) {
        students.forEach(this::addStudent);
    }

    protected void addStudent(Student student) {
        var universityId = student.getUniversity().getId();
        var studentsInUniversities = getStudentsInUniversitiesByScholarshipId(universityId);
        studentsInUniversities.addStudentById(student, universityId);
        studentsInDirectionsAndUniversities.put(student.getScienceDirection().getId(), studentsInUniversities);
    }

    protected DistributionUnit getStudentsInUniversitiesByScholarshipId(Long id) {
        if (studentsInDirectionsAndUniversities.containsKey(id)) {
            return studentsInDirectionsAndUniversities.get(id);
        }
        return new DistributionUnit();
    }

    protected DistributionUnit getStudentsByKeyDirection(Long keyDirection) throws CoreException {
        if (studentsInDirectionsAndUniversities.containsKey(keyDirection)) {
            return studentsInDirectionsAndUniversities.get(keyDirection);
        }
        // TODO add in constants
        throw new CoreException("Направление не найдено");
    }

    public Map<Long, Map<Long, List<Student>>> getStudentsInDirectionAndUniversity() {
        Map<Long, Map<Long, List<Student>>> result = new HashMap<>();
        Map<Long, DistributionUnit> distributionStudentsMap = getStudentsInDirectionsAndDistributionUnit();
        distributionStudentsMap.forEach((k, v) -> {
            result.put(k, v.getDistributionUnit());
        });
        return result;
    }

}
