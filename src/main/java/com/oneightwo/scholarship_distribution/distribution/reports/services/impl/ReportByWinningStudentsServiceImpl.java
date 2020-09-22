package com.oneightwo.scholarship_distribution.distribution.reports.services.impl;

import com.oneightwo.scholarship_distribution.core.helpers.TransformationHelper;
import com.oneightwo.scholarship_distribution.distribution.calculations.services.StudentsInDirectionsAndUniversitiesService;
import com.oneightwo.scholarship_distribution.data_view.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportByWinningStudents;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByWinningStudentsService;
import com.oneightwo.scholarship_distribution.students.models.Student;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportByWinningStudentsServiceImpl implements ReportByWinningStudentsService {

    private final StudentsInDirectionsAndUniversitiesService studentsInDirectionsAndUniversitiesService;
    private final StudentService studentService;
    private final UniversityService universityService;

    public ReportByWinningStudentsServiceImpl(StudentsInDirectionsAndUniversitiesService studentsInDirectionsAndUniversitiesService,
                                              StudentService studentService,
                                              UniversityService universityService) {
        this.studentsInDirectionsAndUniversitiesService = studentsInDirectionsAndUniversitiesService;
        this.studentService = studentService;
        this.universityService = universityService;
    }

    @Override
    public ReportByWinningStudents execute(Semester semester, int year) {
        ReportByWinningStudents reportByWinningStudents = new ReportByWinningStudents();

        studentsInDirectionsAndUniversitiesService.setStudents(studentService.getStudentsBySemesterAndYear(semester, year));

        Map<Long, Map<Long, List<Student>>> studentsInDirectionAndUniversity =
                studentsInDirectionsAndUniversitiesService.getPassedStudents().getStudentsInDirectionAndUniversity();
        Map<Long, Map<Long, Integer>> assignedNumberScholarships =
                studentsInDirectionsAndUniversitiesService.getAssignedNumberScholarships();

        assignedNumberScholarships.forEach((sd, v) -> {
            v.forEach((u, quantity) -> {
                List<Student> students = new ArrayList<>(studentsInDirectionAndUniversity.get(sd).get(u));
                students.sort((o1, o2) -> o2.getRating() - o1.getRating());
                reportByWinningStudents.addStudents(TransformationHelper.studentsToDtos(trimTo(students, quantity)));
            });
        });
        reportByWinningStudents.setUniversities(TransformationHelper.universitiesToDtos(universityService.getExisting()));
        return reportByWinningStudents;
    }

    private List<Student> trimTo(List<Student> students, int to) {
        List<Student> result = new LinkedList<>();
        var counter = 0;
        for (Student v : students) {
            if (counter < to) {
                counter++;
                result.add(v);
            } else {
                break;
            }
        }
        return result;
    }
}
