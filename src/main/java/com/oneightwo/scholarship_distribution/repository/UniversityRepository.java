package com.oneightwo.scholarship_distribution.repository;

import com.oneightwo.scholarship_distribution.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UniversityRepository extends JpaRepository<University, BigInteger> {

}
