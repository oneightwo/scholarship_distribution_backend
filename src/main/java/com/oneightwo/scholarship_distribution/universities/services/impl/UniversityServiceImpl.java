package com.oneightwo.scholarship_distribution.universities.services.impl;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.universities.models.University;
import com.oneightwo.scholarship_distribution.universities.repositories.UniversityRepository;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University getById(Long id) throws CoreException {
        return universityRepository.findById(id).orElseThrow(CoreException::new);
    }

    @Override
    public University save(University university) {
        return universityRepository.save(university);
    }

    @Override
    public List<University> getExisting() {
        return universityRepository.getExisting();
    }

    @Override
    public List<University> getExistingAndDeleted() {
        return universityRepository.findAll();
    }

    @Override
    public University update(University university) throws CoreException {
        getById(university.getId());
        return universityRepository.save(university);
    }

    @Override
    public void markDeleteById(Long id) throws CoreException {
        University university = getById(id);
        university.setDeleted(true);
        universityRepository.save(university);
    }

    @Override
    public void deleteById(Long id) throws CoreException {
        getById(id);
        universityRepository.deleteById(id);
    }
}
