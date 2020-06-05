package com.oneightwo.scholarship_distribution.repository;

import com.oneightwo.scholarship_distribution.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, BigInteger> {
    @Query(value = "SELECT * FROM courses c WHERE c.name IN (:names)",  nativeQuery = true)
    List<Course> findByNumbers(@Param("names") Collection<Integer> names);
}
