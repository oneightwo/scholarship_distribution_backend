package com.oneightwo.scholarship_distribution.core.helpers;

import com.oneightwo.scholarship_distribution.courses.models.Course;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirectionDTO;
import com.oneightwo.scholarship_distribution.students.models.Student;
import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import com.oneightwo.scholarship_distribution.universities.models.University;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
final public class TransformationHelper {

    public static StudentDTO objectToDto(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getSurname(),
                student.getName(),
                student.getPatronymic(),
                student.getUniversity().getId(),
                student.getFaculty(),
                student.getCourse().getId(),
                student.getEmail(),
                student.getPhone(),
                student.getScienceDirection().getId(),
                student.getTopic(),
                student.getC1(),
                student.getC2(),
                student.getC3(),
                student.getC4(),
                student.getC5(),
                student.getC6(),
                student.getC7(),
                student.getC8(),
                student.getC9(),
                student.getC10(),
                student.getC11(),
                student.getC12(),
                student.getC13(),
                student.getC14(),
                student.getC15(),
                student.getRating(),
                student.isValid()
        );
    }

    public static Student dtoToObject(StudentDTO studentDTO) {
        var student = new Student();
        student.setId(studentDTO.getId());
        student.setSurname(studentDTO.getSurname());
        student.setName(studentDTO.getName());
        student.setPatronymic(studentDTO.getPatronymic());
        var university = new University();
        university.setId(studentDTO.getUniversityId());
        student.setUniversity(university);
        student.setFaculty(studentDTO.getFaculty());
        var course = new Course();
        course.setId(studentDTO.getCourseId());
        student.setCourse(course);
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        var sd = new ScienceDirection();
        sd.setId(studentDTO.getScienceDirectionId());
        student.setScienceDirection(sd);
        student.setTopic(studentDTO.getTopic());
        student.setC1(studentDTO.getC1());
        student.setC2(studentDTO.getC2());
        student.setC3(studentDTO.getC3());
        student.setC4(studentDTO.getC4());
        student.setC5(studentDTO.getC5());
        student.setC6(studentDTO.getC6());
        student.setC7(studentDTO.getC7());
        student.setC8(studentDTO.getC8());
        student.setC9(studentDTO.getC9());
        student.setC10(studentDTO.getC10());
        student.setC11(studentDTO.getC11());
        student.setC12(studentDTO.getC12());
        student.setC13(studentDTO.getC13());
        student.setC14(studentDTO.getC14());
        student.setC15(studentDTO.getC15());
        student.setRating(studentDTO.getRating());
        student.setValid(studentDTO.isValid());
        return student;
    }

    public static List<StudentDTO> objectsToDtos(List<Student> students) {
        List<StudentDTO> studentDTOs = new ArrayList<>();
        students.forEach(student -> studentDTOs.add(objectToDto(student)));
        return studentDTOs;
    }

    public static ScienceDirectionDTO objectToDto(ScienceDirection scienceDirection) {
        return new ScienceDirectionDTO(
                scienceDirection.getId(),
                scienceDirection.getName(),
                scienceDirection.isDeleted()
        );
    }

    public static ScienceDirection dtoToObject(ScienceDirectionDTO scienceDirectionDTO) {
        return new ScienceDirection(
                scienceDirectionDTO.getId(),
                scienceDirectionDTO.getName(),
                scienceDirectionDTO.isDeleted()
        );
    }

    public static List<ScienceDirectionDTO> objectToDtos(List<ScienceDirection> scienceDirections) {
        List<ScienceDirectionDTO> scienceDirectionDTOs = new ArrayList<>();
        scienceDirections.forEach(student -> scienceDirectionDTOs.add(objectToDto(student)));
        return scienceDirectionDTOs;
    }
}