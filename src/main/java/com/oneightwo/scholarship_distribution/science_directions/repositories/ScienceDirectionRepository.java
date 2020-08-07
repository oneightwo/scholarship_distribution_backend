package com.oneightwo.scholarship_distribution.science_directions.repositories;

import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScienceDirectionRepository extends JpaRepository<ScienceDirection, Long> {

    @Query(value = "select * from science_directions where NOT deleted ",
            nativeQuery = true)
    List<ScienceDirection> getExisting();
}
