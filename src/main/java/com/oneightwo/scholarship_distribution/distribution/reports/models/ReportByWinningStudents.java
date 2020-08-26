package com.oneightwo.scholarship_distribution.distribution.reports.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import com.oneightwo.scholarship_distribution.universities.models.UniversityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.TreeSet;

@NoArgsConstructor
@Getter
public class ReportByWinningStudents {

    private final TreeSet<ReportUnit<Long, TreeSet<BaseStudentInformation>>> listWinners = new TreeSet<>();

    private TreeSet<UniversityDTO> universities = new TreeSet<>();

    @JsonIgnore
    public void addStudents(List<StudentDTO> studentDTOList) {
        studentDTOList.forEach(this::addStudent);
    }

    @JsonIgnore
    public void addStudent(StudentDTO studentDTO) {
        BaseStudentInformation baseStudentInformation = new BaseStudentInformation(
                studentDTO.getName(),
                studentDTO.getFaculty(),
                studentDTO.getCourseId(),
                studentDTO.getTopic(),
                studentDTO.getRating());
        getStudentsOrDefault(studentDTO.getUniversityId()).getValue().add(baseStudentInformation);
    }

    private ReportUnit<Long, TreeSet<BaseStudentInformation>> getStudentsOrDefault(Long uId) {
        ReportUnit<Long, TreeSet<BaseStudentInformation>> reportUnit = new ReportUnit<>(uId, new TreeSet<>());
        for (ReportUnit<Long, TreeSet<BaseStudentInformation>> v : listWinners) {
            if (v.getId().equals(uId)) {
                reportUnit = v;
                break;
            }
        }
        if (reportUnit.getValue().isEmpty()) {
            listWinners.add(reportUnit);
        }
        return reportUnit;
    }

    public void setUniversities(List<UniversityDTO> universities) {
        this.universities = new TreeSet<>(universities);
    }
}
