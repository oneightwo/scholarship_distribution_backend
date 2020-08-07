package com.oneightwo.scholarship_distribution.universities.repositories;

import com.oneightwo.scholarship_distribution.universities.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {

    @Query(value = "select * from universities where NOT deleted",
            nativeQuery = true)
    List<University> getExisting();
}
