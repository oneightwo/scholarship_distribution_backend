package com.oneightwo.scholarship_distribution.service.impl;

import com.oneightwo.scholarship_distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.core.exceptions.StudentNotFoundException;
import com.oneightwo.scholarship_distribution.distribution.DistributionService;
import com.oneightwo.scholarship_distribution.model.Student;
import com.oneightwo.scholarship_distribution.repository.StudentRepository;
import com.oneightwo.scholarship_distribution.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DistributionService distributionService;

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);


    @Transactional(readOnly = true)
    @Override
    public Student getById(BigInteger id) throws CoreException {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Transactional
    @Override
    public Student save(Student student) {
        student.setDataRegistration(LocalDateTime.now());
        student.setRating(
                distributionService.calculationRating(new int[]{
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
                        }
                ));
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public void deleteById(BigInteger id) throws CoreException {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Transactional
    @Override
    public Student update(Student student) throws CoreException {
        studentRepository.findById(student.getId()).orElseThrow(StudentNotFoundException::new);
        student.setRating(distributionService.calculationRating(new int[]{
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
                }
        ));
        student.setValid(true);
        return studentRepository.save(student);
    }

    @Override
    public boolean isConsist(Student student) {
//        log.info("IN");
//        List<Student> studentList = getAll();
//        for (Student st : studentList) {
//            if (st.getSurname().equals(student.getSurname()) && st.getName().equals(student.getName()) &&
//                    st.getPatronymic().equals(student.getPatronymic())) {
//                log.info("CONSIST TRUE");
//                return true;
//            }
//        }
//        log.info("CONSIST FALSE");
        return false;
    }

    @Override
    public List<Student> getStudentBySemesterAndYear(Semester semester, int year, Pageable pageable) {
        return studentRepository.getStudentByMonthsAndYear(semester.getMonths(), year, pageable);
    }

    @Override
    public List<Student> getStudentBySemesterAndYear(Semester semester, int year) {
        return studentRepository.getStudentByMonthsAndYear(semester.getMonths(), year);
    }
}
