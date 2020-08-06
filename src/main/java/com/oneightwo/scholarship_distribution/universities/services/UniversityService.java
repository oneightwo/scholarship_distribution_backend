package com.oneightwo.scholarship_distribution.universities.services;

import com.oneightwo.scholarship_distribution.universities.models.University;

import java.util.List;
import java.util.Optional;

public interface UniversityService {
    Optional<University> getById(Long id);

    List<University> getAll();

    University save(University university);

    boolean deleteById(Long id);

    University update(University university);
}
