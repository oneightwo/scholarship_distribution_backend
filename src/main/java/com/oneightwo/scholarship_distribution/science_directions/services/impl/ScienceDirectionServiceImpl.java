package com.oneightwo.scholarship_distribution.science_directions.services.impl;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import com.oneightwo.scholarship_distribution.science_directions.repositories.ScienceDirectionRepository;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScienceDirectionServiceImpl implements ScienceDirectionService {

    @Autowired
    private ScienceDirectionRepository scienceDirectionRepository;

    @Override
    public List<ScienceDirection> getAll() {
        return scienceDirectionRepository.findAll();
    }

    @Override
    public ScienceDirection update(ScienceDirection scienceDirection) {
        return scienceDirectionRepository.save(scienceDirection);
    }

    @Override
    public ScienceDirection getById(Long id) throws CoreException {
        return scienceDirectionRepository.findById(id).orElseThrow(CoreException::new);
    }
}
