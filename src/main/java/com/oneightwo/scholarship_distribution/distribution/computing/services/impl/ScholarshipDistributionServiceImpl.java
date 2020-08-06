package com.oneightwo.scholarship_distribution.distribution.computing.services.impl;

import com.oneightwo.scholarship_distribution.distribution.computing.services.ScholarshipDistributionService;
import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScholarshipDistributionServiceImpl implements ScholarshipDistributionService {

    @Autowired
    private StudentService studentService;

    private final StudentsInDirectionsServiceImpl studentsInDirectionsServiceImpl;
    private final StudentsInDirectionsAndUniversitiesServiceImpl studentsInDirectionsAndUniversitiesServiceImpl;

    public ScholarshipDistributionServiceImpl(StudentsInDirectionsServiceImpl studentsInDirectionsServiceImpl,
                                              StudentsInDirectionsAndUniversitiesServiceImpl studentsInDirectionsAndUniversitiesServiceImpl) {
        this.studentsInDirectionsServiceImpl = studentsInDirectionsServiceImpl;
        this.studentsInDirectionsAndUniversitiesServiceImpl = studentsInDirectionsAndUniversitiesServiceImpl;
    }

    @Override
    public Map<Long, Map<Long, Integer>> execute(Semester semester, int year) {
        studentsInDirectionsServiceImpl.setStudents(studentService.getAll());
        return studentsInDirectionsAndUniversitiesServiceImpl.getAssignedNumberScholarships();
    }
}
