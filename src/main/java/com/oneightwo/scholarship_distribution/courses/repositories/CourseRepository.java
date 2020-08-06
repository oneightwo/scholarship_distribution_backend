package com.oneightwo.scholarship_distribution.courses.repositories;

import com.oneightwo.scholarship_distribution.courses.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT * FROM courses c WHERE c.name IN (:names)",  nativeQuery = true)
    List<Course> findByNumbers(@Param("names") Collection<Integer> names);
}
