package com.oneightwo.scholarship_distribution.service.impl;

import com.oneightwo.scholarship_distribution.distribution.DistributionService;
import com.oneightwo.scholarship_distribution.model.Student;
import com.oneightwo.scholarship_distribution.repository.StudentRepository;
import com.oneightwo.scholarship_distribution.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DistributionService distributionService;

    private Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Transactional(readOnly = true)
    @Override
    public Optional<Student> getById(BigInteger id) {
        return studentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getAll() {
        List<Student> studentList = studentRepository.findAll();
        studentList.sort(Comparator.comparing(Student::getSurname));
        return studentList;
    }

    @Transactional
    @Override
    public Student save(Student student) {
//        if (!isConsist(student)) {
            student.setDataRegistration(LocalDateTime.now());
            student.setRating(distributionService.getRating(new int[]{
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
//        }
//        return new Student();
    }

    @Transactional
    @Override
    public boolean deleteById(BigInteger id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Transactional
    @Override
    public Student update(Student student) {
        student.setRating(distributionService.getRating(new int[]{
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
        log.info("IN");
        List<Student> studentList = getAll();
        for (Student st : studentList) {
            if (st.getSurname().equals(student.getSurname()) && st.getName().equals(student.getName()) &&
                    st.getPatronymic().equals(student.getPatronymic())) {
                log.info("CONSIST TRUE");
                return true;
            }
        }
        log.info("CONSIST FALSE");
        return false;
    }

    @Override
    public List<Student> getStudentByMonthsAndYear(List<Integer> months, int year) {
        return studentRepository.getStudentByMonthsAndYear(months, year);
    }

}
