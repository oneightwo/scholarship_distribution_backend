package com.oneightwo.scholarship_distribution.science_directions.services;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;

import java.util.List;

public interface ScienceDirectionService {

    ScienceDirection getById(Long id) throws CoreException;

    @Deprecated
    List<ScienceDirection> getAll();

    ScienceDirection update(ScienceDirection scienceDirection);
}
