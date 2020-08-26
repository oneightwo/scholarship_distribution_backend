package com.oneightwo.scholarship_distribution.students.services.impl;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.core.exceptions.StudentNotFoundException;
import com.oneightwo.scholarship_distribution.distribution.calculations.services.CalculateRatingService;
import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.students.models.Student;
import com.oneightwo.scholarship_distribution.students.repositories.StudentRepository;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CalculateRatingService calculateRatingService;

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Student getById(Long id) throws CoreException {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Transactional
    @Override
    public Student save(Student student) {
        student.setDataRegistration(LocalDateTime.now());
        student.setRating(getRating(student));
        student.setIsValid(false);
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public void delete(Long id) throws CoreException {
        getById(id);
        studentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Student update(Student student) throws CoreException {
        Student dbStudent = getById(student.getId());
        student.setDataRegistration(dbStudent.getDataRegistration());
        student.setRating(getRating(student));
        return studentRepository.save(student);
    }

    private int getRating(Student student) {
        return calculateRatingService.calculate(
                new CalculateRatingService.Criteria(
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
                        student.getC15()
                ));
    }

    @Override
    public List<Student> getStudentsBySemesterAndYear(Semester semester, int year) {
        return studentRepository.getStudentByMonthsAndYear(semester.getMonths(), year);
    }

    @Override
    public List<Student> getStudentsBySemesterAndYear(Semester semester, int year, Pageable pageable) {
        return studentRepository.getStudentByMonthsAndYear(semester.getMonths(), year, pageable);
    }
}
