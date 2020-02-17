package com.oneightwo.scholarship_distribution.service;

import com.oneightwo.scholarship_distribution.model.University;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UniversityService {
    Optional<University> getById(BigInteger id);

    List<University> getAll();

    University save(University university);

    boolean deleteById(BigInteger id);

    University update(University university);
}
