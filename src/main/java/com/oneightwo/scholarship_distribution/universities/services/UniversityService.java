package com.oneightwo.scholarship_distribution.universities.services;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.universities.models.University;

import java.util.List;

public interface UniversityService {

    University getById(Long id) throws CoreException;

    University save(University university);

    List<University> getExisting();

    List<University> getExistingAndDeleted();

    University update(University university) throws CoreException;

    void markDeleteById(Long id) throws CoreException;

    void deleteById(Long id) throws CoreException;

    List<University> getCurrent();
}
