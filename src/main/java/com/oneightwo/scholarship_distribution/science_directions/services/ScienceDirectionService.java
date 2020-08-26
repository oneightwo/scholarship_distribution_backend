package com.oneightwo.scholarship_distribution.science_directions.services;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;

import java.util.List;

public interface ScienceDirectionService {

    ScienceDirection getById(Long id) throws CoreException;

    ScienceDirection save(ScienceDirection scienceDirection);

    List<ScienceDirection> getExistingAndDeleted();

    ScienceDirection update(ScienceDirection scienceDirection) throws CoreException;

    void markDeleteById(Long id) throws CoreException;

    void deleteById(Long id) throws CoreException;

    List<ScienceDirection> getCurrent();
}
