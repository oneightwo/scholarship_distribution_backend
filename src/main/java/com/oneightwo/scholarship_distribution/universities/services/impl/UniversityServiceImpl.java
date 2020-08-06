package com.oneightwo.scholarship_distribution.universities.services.impl;

import com.oneightwo.scholarship_distribution.students.services.impl.StudentServiceImpl;
import com.oneightwo.scholarship_distribution.universities.models.University;
import com.oneightwo.scholarship_distribution.universities.repositories.UniversityRepository;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    private Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Transactional(readOnly = true)
    @Override
    public Optional<University> getById(Long id) {
        return universityRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<University> getAll() {
        List<University> universityList = universityRepository.findAll();
        universityList.sort(Comparator.comparing(University::getName));
        return universityList;
    }

    @Transactional
    @Override
    public University save(University university) {
        return universityRepository.save(university);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (universityRepository.findById(id).isPresent()) {
            universityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public University update(University university) {
        return universityRepository.save(university);
    }
}
