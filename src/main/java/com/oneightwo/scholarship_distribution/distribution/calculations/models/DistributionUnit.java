package com.oneightwo.scholarship_distribution.distribution.calculations.models;

import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.*;

public class DistributionUnit {

    private final Map<Long, List<Student>> distributionUnit;

    public DistributionUnit() {
        this.distributionUnit = new HashMap<>();
    }

    public DistributionUnit(DistributionUnit distributionUnit) {
        this.distributionUnit = new HashMap<>(distributionUnit.getDistributionUnit());
    }

    public Map<Long, List<Student>> getDistributionUnit() {
        return distributionUnit;
    }

    public void addStudentById(Student student, Long id) {
        List<Student> studentList = new ArrayList<>(getStudentsOrDefaultById(id));
        studentList.add(student);
        distributionUnit.put(id, studentList);
    }

    public List<Student> getStudentsOrDefaultById(Long id) {
        if (distributionUnit.containsKey(id)) {
            return distributionUnit.get(id);
        }
        return new ArrayList<>();
    }

    public Map<Long, List<Student>> deleteById(Long id) {
        distributionUnit.remove(id);
        return distributionUnit;
    }

    public Map<Long, Double> getRelationshipRatings() {
        Map<Long, Double> result = new HashMap<>();
        distributionUnit.forEach((key, studentList) -> {
            result.put(key, (double) sumRatings(studentList) / sumAllRatings());
        });
        return result;
    }

    private Integer sumRatings(List<Student> students) {
        var rating = 0;
        for (Student student : students) {
            rating += student.getRating();
        }
        return rating;
    }

    private Integer sumAllRatings() {
        var rating = 0;
        for (Map.Entry<Long, List<Student>> entry : distributionUnit.entrySet()) {
            var studentList = entry.getValue();
            rating += studentList.stream().mapToInt(Student::getRating).sum();
        }
        return rating;
    }

    public Integer getQuantityStudents() {
        int sum = 0;
        for (List<Student> students : distributionUnit.values()) {
            sum += students.size();
        }
        return sum;
    }

}
