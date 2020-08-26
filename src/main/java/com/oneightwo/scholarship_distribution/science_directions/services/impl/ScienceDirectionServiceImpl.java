package com.oneightwo.scholarship_distribution.science_directions.services.impl;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import com.oneightwo.scholarship_distribution.science_directions.repositories.ScienceDirectionRepository;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class ScienceDirectionServiceImpl implements ScienceDirectionService {

    private final ScienceDirectionRepository scienceDirectionRepository;

    public ScienceDirectionServiceImpl(ScienceDirectionRepository scienceDirectionRepository) {
        this.scienceDirectionRepository = scienceDirectionRepository;
    }

    @Override
    public ScienceDirection getById(Long id) throws CoreException {
        return scienceDirectionRepository.findById(id).orElseThrow(CoreException::new);
    }

    @Override
    public List<ScienceDirection> getExistingAndDeleted() {
        return scienceDirectionRepository.findAll();
    }

//    @Override
//    public List<ScienceDirection> getExisting() {
//        return scienceDirectionRepository.getExisting();
//    }

    @Transactional
    @Override
    public ScienceDirection save(ScienceDirection scienceDirection) {
        return scienceDirectionRepository.save(scienceDirection);
    }

    @Transactional
    @Override
    public ScienceDirection update(ScienceDirection scienceDirection) throws CoreException {
        getById(scienceDirection.getId());
        return scienceDirectionRepository.save(scienceDirection);
    }

    @Transactional
    @Override
    public void markDeleteById(Long id) throws CoreException {
        ScienceDirection scienceDirection = getById(id);
        scienceDirection.setDeleted(true);
        scienceDirectionRepository.save(scienceDirection);
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws CoreException {
        getById(id);
        scienceDirectionRepository.deleteById(id);
    }

    @Override
    public List<ScienceDirection> getCurrent() {
        return scienceDirectionRepository.getExisting();
    }
}
