package com.oneightwo.scholarship_distribution.students.repositories;

import com.oneightwo.scholarship_distribution.students.models.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long>, JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM students WHERE Extract(MONTH from data_registration) IN (:months)" +
            "AND Extract(YEAR from data_registration) IN (:year)",
            nativeQuery = true)
    List<Student> getStudentByMonthsAndYear(@Param("months") List<Integer> months, @Param("year") int year, Pageable pageable);

    @Query(value = "SELECT * FROM students WHERE Extract(MONTH from data_registration) IN (:months)" +
            "AND Extract(YEAR from data_registration) IN (:year) order by surname",
            nativeQuery = true)
    List<Student> getStudentByMonthsAndYear(@Param("months") List<Integer> months, @Param("year") int year);
}
