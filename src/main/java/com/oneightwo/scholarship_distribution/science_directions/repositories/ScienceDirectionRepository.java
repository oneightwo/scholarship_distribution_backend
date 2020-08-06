package com.oneightwo.scholarship_distribution.science_directions.repositories;

import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceDirectionRepository extends JpaRepository<ScienceDirection, Long> {

}
