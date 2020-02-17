package com.oneightwo.scholarship_distribution.service;

import com.oneightwo.scholarship_distribution.model.ScienceDirection;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ScienceDirectionService {
    Optional<ScienceDirection> getById(BigInteger id);

    List<ScienceDirection> getAll();

    ScienceDirection update(ScienceDirection scienceDirection);
}
