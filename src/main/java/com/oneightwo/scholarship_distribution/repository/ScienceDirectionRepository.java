package com.oneightwo.scholarship_distribution.repository;

import com.oneightwo.scholarship_distribution.model.ScienceDirection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ScienceDirectionRepository extends JpaRepository<ScienceDirection, BigInteger> {

}
