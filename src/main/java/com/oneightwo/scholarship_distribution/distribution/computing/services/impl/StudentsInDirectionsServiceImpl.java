package com.oneightwo.scholarship_distribution.distribution.computing.services.impl;

import com.oneightwo.scholarship_distribution.distribution.computing.operations.DistributionOperation;
import com.oneightwo.scholarship_distribution.distribution.computing.models.DistributionUnit;
import com.oneightwo.scholarship_distribution.distribution.computing.services.StudentsInDirectionsService;
import com.oneightwo.scholarship_distribution.distribution.constants.Constants;
import com.oneightwo.scholarship_distribution.students.models.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentsInDirectionsServiceImpl implements StudentsInDirectionsService {

    private final DistributionOperation distributionOperation;
    private final DistributionUnit allStudentsInDirections = new DistributionUnit();
    private final DistributionUnit passedStudentsInDirections = new DistributionUnit();
    private final List<Student> studentList = new ArrayList<>();

    public StudentsInDirectionsServiceImpl(DistributionOperation distributionOperation) {
        this.distributionOperation = distributionOperation;
    }

    @Override
    public void setStudents(List<Student> students) {
        studentList.addAll(students);
        addStudents(students, allStudentsInDirections);
        addStudents(getPassedStudents(), passedStudentsInDirections);
    }

//    public List<Student> getStudentsByDirectionId(Long idDirection) {
//        return allStudentsInDirections.getStudentsOrDefaultById(idDirection);
//    }

    private void addStudents(List<Student> students, DistributionUnit appointment) {
        students.forEach(student ->
                appointment.addStudentById(student, student.getScienceDirection().getId())
        );
    }

    @Override
    public List<Student> getPassedStudents() {
        List<Student> students = new ArrayList<>();
        if (isEnoughStudents()) {
            return calculationPassedStudentsBasedOnExcluded();
        } else {
            allStudentsInDirections.getDistributionUnit().values().forEach(students::addAll);
        }
        return students;
    }

    private boolean isEnoughStudents() {
        return calculationPassedStudentsBasedOnExcluded().size() > Constants.NUMBER_SCHOLARSHIPS;
    }

    private List<Student> calculationPassedStudentsBasedOnExcluded() {
        List<Student> students = new ArrayList<>();
        getQuantityExcludedStudents().forEach((k, v) -> {
            students.addAll(allStudentsInDirections.getStudentsOrDefaultById(k).subList(0, v));
        });
        return students;
    }

    @Override
    public List<Student> getExcludedStudents() {
        List<Student> students = new ArrayList<>();
        getQuantityExcludedStudents().forEach((k, v) -> {
            students.addAll(allStudentsInDirections.getStudentsOrDefaultById(k).subList(0, v));
        });
        return students;
    }

    @Override
    public Map<Long, Double> getMinimalRatings() {
        Map<Long, Double> minimalRatingInDirections = new HashMap<>();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            var sumRating = v.stream().mapToInt(Student::getRating).sum();
            var quantityStudents = v.size();
            minimalRatingInDirections.put(k, (double) sumRating / quantityStudents * Constants.MINIMUM_PERCENTAGE_BORDER);
        });
        return minimalRatingInDirections;
    }

    @Override
    public Map<Long, Double> getAverageRatings() {
        Map<Long, Double> averageRatingInDirections = new HashMap<>();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            averageRatingInDirections.put(k, (double) v.stream().mapToInt(Student::getRating).sum() / v.size());
        });
        return averageRatingInDirections;
    }

    @Override
    public Map<Long, Integer> getNumberApplications() {
        Map<Long, Integer> numberApplicationsInDirections = new HashMap<>();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            numberApplicationsInDirections.put(k, v.size());
        });
        return numberApplicationsInDirections;
    }

    @Override
    public Map<Long, Integer> getQuantityExcludedStudents() {
        Map<Long, Integer> excludedStudents = new HashMap<>();
        Map<Long, Double> minimalRatings = getMinimalRatings();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            v.forEach(student -> {
                if (student.getRating() < minimalRatings.get(k))
                    excludedStudents.put(k, excludedStudents.getOrDefault(k, 0) + 1);
            });
        });
        return excludedStudents;
    }

    @Override
    public Map<Long, Integer> getQuantityPassedStudents() {
        Map<Long, Integer> passedStudents = new HashMap<>();
        Map<Long, Double> minimalRatings = getMinimalRatings();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            v.forEach(student -> {
                if (student.getRating() >= minimalRatings.get(k))
                    passedStudents.put(k, passedStudents.getOrDefault(k, 0) + 1);
            });
        });
        return passedStudents;
    }

    @Override
    public Map<Long, Integer> getAssignedNumberScholarships() {
        return distributionOperation.execute(passedStudentsInDirections, Constants.NUMBER_SCHOLARSHIPS);
    }

    public List<Student> getStudents() {
        return studentList;
    }
}
