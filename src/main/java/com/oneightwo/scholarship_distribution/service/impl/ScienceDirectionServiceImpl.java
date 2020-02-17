package com.oneightwo.scholarship_distribution.service.impl;

import com.oneightwo.scholarship_distribution.model.ScienceDirection;
import com.oneightwo.scholarship_distribution.repository.ScienceDirectionRepository;
import com.oneightwo.scholarship_distribution.service.ScienceDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ScienceDirectionServiceImpl implements ScienceDirectionService {

    @Autowired
    private ScienceDirectionRepository scienceDirectionRepository;

    @Override
    public List<ScienceDirection> getAll() {
        List<ScienceDirection> scienceDirectionList = scienceDirectionRepository.findAll();
        scienceDirectionList.sort(Comparator.comparing(ScienceDirection::getName));
        return scienceDirectionList;
    }

    @Override
    public ScienceDirection update(ScienceDirection scienceDirection) {
        return scienceDirectionRepository.save(scienceDirection);
    }

    @Override
    public Optional<ScienceDirection> getById(BigInteger id) {
        return scienceDirectionRepository.findById(id);
    }
}
