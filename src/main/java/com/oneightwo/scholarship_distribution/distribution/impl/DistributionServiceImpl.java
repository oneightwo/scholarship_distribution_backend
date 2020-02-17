package com.oneightwo.scholarship_distribution.distribution.impl;

import com.oneightwo.scholarship_distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.DistributionService;
import com.oneightwo.scholarship_distribution.model.Student;
import com.oneightwo.scholarship_distribution.service.impl.ScienceDirectionServiceImpl;
import com.oneightwo.scholarship_distribution.service.impl.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.oneightwo.scholarship_distribution.constants.Constants.*;
import static com.oneightwo.scholarship_distribution.tools.Tools.sumDouble;
import static com.oneightwo.scholarship_distribution.tools.Tools.sumLong;

@Component
public class DistributionServiceImpl implements DistributionService {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private ScienceDirectionServiceImpl scienceDirectionService;

    private Logger log = LoggerFactory.getLogger(DistributionServiceImpl.class);

    @Override
    public int getRating(int[] criteria) {
        double rating = 0.0;
        for (int i = 0; i < criteria.length; i++) {
            rating += KEF[VK[i]] * UR[criteria[i]];
        }
        return (int) Math.round(rating);
    }

    private double getAverageRating(List<Student> students) {
        double rating = 0;
        for (Student student : students) {
            rating += student.getRating();
        }
        return rating / students.size();
    }

    private double getMinimalRating(List<Student> students) {
        double rating = 0;
        for (Student student : students) {
            rating += student.getRating();
        }
        return rating / students.size() * MINIMUM_PERCENTAGE_BORDER;
    }

    private Map<String, List<Student>> divisionOfDirection(List<Student> studentList) {
        Map<String, List<Student>> divisionList = new HashMap<>() {{
            scienceDirectionService.getAll().forEach(scienceDirection ->
                    put(scienceDirection.getName(), new ArrayList<>())
            );
        }};
        log.info(divisionList.toString());
        for (Student student : studentList) {
            String nameDirection = student.getScienceDirection().getName();
            divisionList.get(nameDirection).add(student);
        }
        return divisionList;
    }

    private Map<String, Map<String, List<Student>>> selectionByMinimalRatingAndDirection(Map<String, List<Student>> divisionOfDirectionList) {
        Map<String, Map<String, List<Student>>> selectionList = new HashMap<>() {{
            put(PASSED, new HashMap<>() {{
                for (String key : divisionOfDirectionList.keySet()) {
                    put(key, new ArrayList<>());
                }
            }});
            put(NOT_PASSED, new HashMap<>() {{
                for (String key : divisionOfDirectionList.keySet()) {
                    put(key, new ArrayList<>());
                }
            }});
        }};
        for (Map.Entry<String, List<Student>> entry : divisionOfDirectionList.entrySet()) {
            log.info(entry.getKey() + " -> " + String.valueOf(getMinimalRating(entry.getValue())));
            for (Student student : entry.getValue()) {
                double minRating = getMinimalRating(entry.getValue());
                String keySD = student.getScienceDirection().getName();
                if (student.getRating() < minRating || !student.isValid()) {
                    selectionList.get(NOT_PASSED).get(keySD).add(student);
                } else {
                    selectionList.get(PASSED).get(keySD).add(student);
                }
            }
        }
        return selectionList;
    }

    private Map<String, Double> calculationRating(Map<String, List<Student>> passedList) {
        Map<String, Double> ratingMap = new HashMap<>();
        for (Map.Entry<String, List<Student>> entry : passedList.entrySet()) {
            double counter = 0;
            List<Student> students = entry.getValue();
            for (Student student : students) {
                counter += student.getRating();
            }
            ratingMap.put(entry.getKey(), counter / students.size());
        }
        return ratingMap;
    }

    private Map<String, Long> distributionScholarshipByAny(Map<String, List<Student>> passedStudent, Long count) {
        Map<String, Long> scholarshipByCandidates = new HashMap<>() {{
            passedStudent.forEach((key, value) -> put(key, -1L));
        }};
        Map<String, Long> result = new HashMap<>() {{
            passedStudent.forEach((key, value) -> put(key, 0L));
        }};
        Map<String, Double> relationshipRatingByCandidates = new HashMap<>();
        Map<String, Double> ratingByDirection = calculationRating(passedStudent);
        Double sumRating = sumDouble(ratingByDirection.values());
        Long free = count;

        while (free != 0) {
            Double finalSumRating = sumRating;
            ratingByDirection.forEach((key, value) -> relationshipRatingByCandidates.put(key, value / finalSumRating));
            Long finalFree = free;
            Long finalFree1 = free;
            if (relationshipRatingByCandidates.values().stream().allMatch(v -> Math.round(v * finalFree1) == 0)) {
                String finalKey = "";
                if (free > 0) {
                    log.info("result => {}", result);
                    scholarshipByCandidates = result.entrySet().stream()
                            .filter(e -> passedStudent.get(e.getKey()).size() > result.get(e.getKey()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    log.info("scholarshipByCandidates => {}", scholarshipByCandidates);
                    Double maxRating = 0.0;
                    for (Map.Entry<String, Double> entry : ratingByDirection.entrySet()) {
                        if (maxRating < entry.getValue() && scholarshipByCandidates.containsKey(entry.getKey())) {
                            maxRating = entry.getValue();
                            finalKey = entry.getKey();
                        }
                    }
                    scholarshipByCandidates.put(finalKey, result.get(finalKey) + 1L);
                } else {
                    Double minRating = 101.0;
                    scholarshipByCandidates = result.entrySet().stream()
                            .filter(e -> e.getValue() >= 1)
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                    Map<String, Long> finalScholarshipByCandidates = scholarshipByCandidates;
                    ratingByDirection = calculationRating(passedStudent.entrySet().stream()
                            .filter(e -> finalScholarshipByCandidates.containsKey(e.getKey()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

                    log.info("scholarshipByCandidates = {}", scholarshipByCandidates);
                    log.info("ratingByDirection = {}", ratingByDirection);

                    for (String key : scholarshipByCandidates.keySet()) {
                        if (ratingByDirection.containsKey(key) && minRating > ratingByDirection.get(key)) {
                            minRating = ratingByDirection.get(key);
                            finalKey = key;
                        }
                    }
                    log.info("minRating = {}", minRating);
                    log.info("keyMinRating = {}", finalKey);
                    scholarshipByCandidates.put(finalKey, scholarshipByCandidates.get(finalKey) - 1L);
                }
                result.put(finalKey, scholarshipByCandidates.get(finalKey));

            } else { //------------------------------------
                for (Map.Entry<String, Double> e : relationshipRatingByCandidates.entrySet()) {
                    String s = e.getKey();
                    Double v = e.getValue();
                    if (scholarshipByCandidates.containsKey(s)) {
                        scholarshipByCandidates.put(s, Math.round(finalFree * relationshipRatingByCandidates.get(s)));
                    }
                }
                for (String k : scholarshipByCandidates.keySet()) {
                    while (scholarshipByCandidates.get(k) > passedStudent.get(k).size() - result.get(k)) {
                        scholarshipByCandidates.put(k, scholarshipByCandidates.get(k) - 1);
                    }
                }
                for (Map.Entry<String, Long> entry : scholarshipByCandidates.entrySet()) {
                    String key = entry.getKey();
                    Long value = entry.getValue();
                    if (result.containsKey(key)) {
                        result.put(key, result.get(key) + value);
                    } else {
                        result.put(key, value);
                    }
                }
            }
            scholarshipByCandidates = scholarshipByCandidates.entrySet().stream()
                    .filter(e -> result.get(e.getKey()) != passedStudent.get(e.getKey()).size())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            Map<String, Long> finalScholarshipByCandidates1 = scholarshipByCandidates;
            ratingByDirection = calculationRating(passedStudent.entrySet().stream()
                    .filter(e -> finalScholarshipByCandidates1.containsKey(e.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
            sumRating = sumDouble(ratingByDirection.values());
            relationshipRatingByCandidates.clear();
            free = count - sumLong(result.values());
            log.info("result -> {}", result);
            log.info("resultSum -> {}", sumLong(result.values()));
            log.info("count -> {}", count);
            log.info("free -> {}", free);
        }
        return result;
    }

    private Map<String, Map<String, List<Student>>> divisionUniversitiesByDirection(Map<String, List<Student>> passedStudent) {
        Map<String, Map<String, List<Student>>> map = new HashMap<>() {{
            scienceDirectionService.getAll().forEach(scienceDirection ->
                    put(scienceDirection.getName(), new HashMap<>())
            );
        }};
        log.info("======divisionUniversitiesByDirection======");
        for (String d : map.keySet()) {
            for (Student student : passedStudent.get(d)) {
                String u = student.getUniversity().getAbbreviation();
                Map<String, List<Student>> listMap = map.get(d);
                if (listMap.containsKey(u)) {
                    listMap.get(u).add(student);
                } else {
                    listMap.put(u, new ArrayList<>());
                    listMap.get(u).add(student);
                }
                map.put(d, listMap);
            }
        }
        for (String k : map.keySet()) {
            int c = 0;
            for (String k1 : map.get(k).keySet()) {
                c += map.get(k).get(k1).size();
            }
            log.info("k={}, size({})", k, c);
        }
        return map;
    }

    private Map<String, Map<String, Long>> distributionScholarshipByUniversities(Map<String, Map<String, List<Student>>> divisionUniversitiesByDirection,
                                                                                 Map<String, Long> distributionScholarshipByDirection) {
        Map<String, Map<String, Long>> distributionScholarshipByUniversities = new HashMap<>();

        log.info("!!! {}", distributionScholarshipByDirection);
        for (Map.Entry<String, Long> entry : distributionScholarshipByDirection.entrySet()) {
            String k = entry.getKey();
            Long v = entry.getValue();
            distributionScholarshipByUniversities.put(k, distributionScholarshipByAny(divisionUniversitiesByDirection.get(k), v));
        }

        for (Map.Entry<String, Map<String, Long>> entry : distributionScholarshipByUniversities.entrySet()) {
            int sum = 0;
            for (Map.Entry<String, Long> entry1 : entry.getValue().entrySet()) {
                sum += entry1.getValue();
            }
            log.info("На {} выделено {}", entry.getKey(), sum);
        }

        return distributionScholarshipByUniversities;
    }

    private Map<String, Map<String, List<Student>>> sortedByDirectionsAndUniversities(Map<String, Map<String, List<Student>>> listByDirectionsAndUniversities) {
        Map<String, Map<String, List<Student>>> sortedByDirectionsAndUniversities = new HashMap<>();
        for (Map.Entry<String, Map<String, List<Student>>> entry : listByDirectionsAndUniversities.entrySet()) {
            List<Student> list = new ArrayList<>();
            Map<String, List<Student>> map = new HashMap<>();
            sortedByDirectionsAndUniversities.put(entry.getKey(), entry.getValue());
            for (Map.Entry<String, List<Student>> entry1 : entry.getValue().entrySet()) {
                list = entry1.getValue().stream()
                        .sorted(Comparator.comparingInt(Student::getRating).reversed())
                        .collect(Collectors.toList());
                map.put(entry1.getKey(), list);
            }
            sortedByDirectionsAndUniversities.put(entry.getKey(), map);
        }
        return sortedByDirectionsAndUniversities;
    }

    private Map<String, List<Student>> getWinnerListSortedByUniversities(Map<String, Map<String, Long>> countPlace,
                                                                         Map<String, Map<String, List<Student>>> sortedMap) {
        Map<String, List<Student>> winnersList = new HashMap<>();
        for (Map.Entry<String, Map<String, List<Student>>> entry : sortedMap.entrySet()) {
            for (Map.Entry<String, List<Student>> entry1 : entry.getValue().entrySet()) {
                if (winnersList.containsKey(entry1.getKey())) {
                    List<Student> list = winnersList.get(entry1.getKey());
                    list.addAll(entry1.getValue().subList(0, countPlace.get(entry.getKey()).get(entry1.getKey()).intValue()));
                    winnersList.put(entry1.getKey(), list);
                } else {
                    winnersList.put(entry1.getKey(), entry1.getValue().subList(0, countPlace.get(entry.getKey()).get(entry1.getKey()).intValue()));
                }
                log.info("направление=({}) вуз=({}) мест=({}) поместится=({})", entry.getKey(), entry1.getKey(), countPlace.get(entry.getKey()).get(entry1.getKey()), winnersList.get(entry1.getKey()).size() + entry1.getValue().subList(0, countPlace.get(entry.getKey()).get(entry1.getKey()).intValue()).size());
            }
        }
        int sum = 0;
        for (Map.Entry<String, List<Student>> entry : winnersList.entrySet()) {
            String key = entry.getKey();
            List<Student> value = entry.getValue();
            log.info("k={}, v={}", key, value.size());
            sum += value.size();
        }
        log.info("sum={}", sum);
        return winnersList;
    }

    private Map<String, Map<String, String>> distributionScholarshipsByStudent(Map<String, List<Student>> winnerMap) {
        Map<String, Map<String, String>> resultList = new HashMap<>();
        for (Map.Entry<String, List<Student>> entry : winnerMap.entrySet()) {
            for (Student student : entry.getValue()) {
                String sd = student.getScienceDirection().getName();
                String u = student.getUniversity().getAbbreviation();
                int sum = 0;
                if (resultList.containsKey(sd)) {
                    if (resultList.get(sd).containsKey(u)) {
                        sum = Integer.parseInt(resultList.get(sd).get(u));
                        sum += 1;
                        resultList.get(sd).put(u, String.valueOf(sum));
                    } else {
                        sum = 1;
                        resultList.get(sd).put(u, String.valueOf(sum));
                    }
                } else {
                    resultList.put(sd, new HashMap<>());
                }
            }
        }

        return resultList;
    }

    @Override
    public Map<String, List<Student>> getWinnerStudents(Semester semester, int year) {
        List<Student> studentList = studentService.getStudentByMonthsAndYear(semester.getMonths(), year);
        Map<String, List<Student>> divisionOfDirectionMap = divisionOfDirection(studentList);
        Map<String, Map<String, List<Student>>> selectionByMinimalRatingAndDirectionMap = selectionByMinimalRatingAndDirection(divisionOfDirectionMap);
//        Map<String, Double> calculationRatingByDirectionMap = calculationRating(selectionByMinimalRatingAndDirectionMap.get(PASSED));
        Map<String, Long> distributionScholarshipByDirection = distributionScholarshipByAny(selectionByMinimalRatingAndDirectionMap.get(PASSED), NUMBER_SCHOLARSHIPS);
        log.info("distributionScholarshipByDirection---->{}", distributionScholarshipByDirection);
        Map<String, Map<String, List<Student>>> divisionUniversitiesByDirection = divisionUniversitiesByDirection(selectionByMinimalRatingAndDirectionMap.get(PASSED));
        Map<String, Map<String, Long>> distributionScholarshipByUniversities = distributionScholarshipByUniversities(divisionUniversitiesByDirection, distributionScholarshipByDirection);
        Map<String, List<Student>> winnerMap = getWinnerListSortedByUniversities(distributionScholarshipByUniversities, sortedByDirectionsAndUniversities(divisionUniversitiesByDirection));
        return winnerMap;
    }

    @Override
    public Map<String, List<Student>> getLoserStudents(Semester semester, int year) {
        List<Student> studentList = studentService.getStudentByMonthsAndYear(semester.getMonths(), year);
        Map<String, List<Student>> divisionOfDirectionMap = divisionOfDirection(studentList);
        Map<String, Map<String, List<Student>>> selectionByMinimalRatingAndDirectionMap = selectionByMinimalRatingAndDirection(divisionOfDirectionMap);
        return selectionByMinimalRatingAndDirectionMap.get(NOT_PASSED);
    }

    @Override
    public Map<String, Map<String, Long>> getCountScholarshipsByDirectionAndUniversities(Semester semester, int year) {
        List<Student> studentList = studentService.getStudentByMonthsAndYear(semester.getMonths(), year);
        Map<String, List<Student>> divisionOfDirectionMap = divisionOfDirection(studentList);
        Map<String, Map<String, List<Student>>> selectionByMinimalRatingAndDirectionMap = selectionByMinimalRatingAndDirection(divisionOfDirectionMap);
//        Map<String, Double> calculationRatingByDirectionMap = calculationRating(selectionByMinimalRatingAndDirectionMap.get(PASSED));
        Map<String, Long> distributionScholarshipByDirection = distributionScholarshipByAny(selectionByMinimalRatingAndDirectionMap.get(PASSED), NUMBER_SCHOLARSHIPS);
        log.info("distributionScholarshipByDirection---->{}", distributionScholarshipByDirection);
        Map<String, Map<String, List<Student>>> divisionUniversitiesByDirection = divisionUniversitiesByDirection(selectionByMinimalRatingAndDirectionMap.get(PASSED));
        Map<String, Map<String, Long>> distributionScholarshipByUniversities = distributionScholarshipByUniversities(divisionUniversitiesByDirection, distributionScholarshipByDirection);
        return distributionScholarshipByUniversities;
    }

    @Override
    public List<Map<String, String>> getReportByDirection(Semester semester, int year) {
        List<Map<String, String>> reportList = new ArrayList<>();
        List<Student> studentList = studentService.getStudentByMonthsAndYear(semester.getMonths(), year);
        Map<String, List<Student>> divisionOfDirectionMap = divisionOfDirection(studentList);
        Map<String, Map<String, List<Student>>> selectionByMinimalRatingAndDirectionMap = selectionByMinimalRatingAndDirection(divisionOfDirectionMap);
        Map<String, Long> distributionScholarshipByDirection = distributionScholarshipByAny(selectionByMinimalRatingAndDirectionMap.get(PASSED), NUMBER_SCHOLARSHIPS);
        Map<String, String> map = new HashMap<>();

        //Кол-во заявок по направлениям
        for (Map.Entry<String, List<Student>> entry : divisionOfDirectionMap.entrySet()) {
            map.put(entry.getKey(), String.valueOf(entry.getValue().size()));
        }
        reportList.add(new HashMap<>() {{
            putAll(map);
        }});
        //Средний рейтинг
        for (Map.Entry<String, List<Student>> entry : divisionOfDirectionMap.entrySet()) {
            map.put(entry.getKey(), String.valueOf(getAverageRating(entry.getValue())));
        }
        reportList.add(new HashMap<>() {{
            putAll(map);
        }});
        //Граница 25%
        for (Map.Entry<String, List<Student>> entry : divisionOfDirectionMap.entrySet()) {
            map.put(entry.getKey(), String.valueOf(getMinimalRating(entry.getValue())));
        }
        reportList.add(new HashMap<>() {{
            putAll(map);
        }});
        //Кол-во исключенных заявок
        for (Map.Entry<String, Map<String, List<Student>>> entry : selectionByMinimalRatingAndDirectionMap.entrySet()) {
            if (entry.getKey().equals(NOT_PASSED)) {
                for (Map.Entry<String, List<Student>> entry1 : entry.getValue().entrySet()) {
                    map.put(entry1.getKey(), String.valueOf(entry1.getValue() != null ? entry1.getValue().size() : 0));
                }
            }
        }
        reportList.add(new HashMap<>() {{
            putAll(map);
        }});
        //Кол-во прошедших заявок
        for (Map.Entry<String, Map<String, List<Student>>> entry : selectionByMinimalRatingAndDirectionMap.entrySet()) {
            if (entry.getKey().equals(PASSED)) {
                for (Map.Entry<String, List<Student>> entry1 : entry.getValue().entrySet()) {
                    map.put(entry1.getKey(), String.valueOf(entry1.getValue() != null ? entry1.getValue().size() : 0));
                }
            }
        }
        reportList.add(new HashMap<>() {{
            putAll(map);
        }});
        //Рекомендованное кол-во стипендий
        for (Map.Entry<String, Long> entry : distributionScholarshipByDirection.entrySet()) {
            map.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        reportList.add(new HashMap<>() {{
            putAll(map);
        }});

        return reportList;
    }

    @Override
    public Map<String, List<Map<String, String>>> getReportByDirectionAndUniversities(Semester semester, int year) {
        Map<String, List<Map<String, String>>> reportList = new HashMap<>();
        List<Student> studentList = studentService.getStudentByMonthsAndYear(semester.getMonths(), year);
        Map<String, List<Student>> divisionOfDirectionMap = divisionOfDirection(studentList);
        Map<String, Map<String, List<Student>>> selectionByMinimalRatingAndDirectionMap = selectionByMinimalRatingAndDirection(divisionOfDirectionMap);
//        Map<String, Double> calculationRatingByDirectionMap = calculationRating(selectionByMinimalRatingAndDirectionMap.get(PASSED));
        Map<String, Long> distributionScholarshipByDirection = distributionScholarshipByAny(selectionByMinimalRatingAndDirectionMap.get(PASSED), NUMBER_SCHOLARSHIPS);
        log.info("distributionScholarshipByDirection---->{}", distributionScholarshipByDirection);
        Map<String, Map<String, List<Student>>> divisionUniversitiesByDirectionMap = divisionUniversitiesByDirection(selectionByMinimalRatingAndDirectionMap.get(PASSED));
        Map<String, Map<String, Long>> distributionScholarshipByUniversities = distributionScholarshipByUniversities(divisionUniversitiesByDirectionMap, distributionScholarshipByDirection);
        Map<String, List<Student>> winnerList = getWinnerListSortedByUniversities(distributionScholarshipByUniversities, sortedByDirectionsAndUniversities(divisionUniversitiesByDirectionMap));
//        return distributionScholarshipsByStudent(winnerList);
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();

        //Средний рейтинг
        for (Map.Entry<String, Map<String, List<Student>>> entry : divisionUniversitiesByDirectionMap.entrySet()) {
            for (Map.Entry<String, List<Student>> entry1 : entry.getValue().entrySet()) {
                map.put(entry1.getKey(), String.valueOf(getAverageRating(entry1.getValue())));
            }
            list.add(new HashMap<>() {{
                putAll(map);
            }});
            reportList.put(entry.getKey(), new ArrayList<>() {{
                addAll(list);
            }});
            list.clear();
            map.clear();
        }
        //Кол-во заявок допущенных до конкурса
        for (Map.Entry<String, Map<String, List<Student>>> entry : sortedByDirectionsAndUniversities(divisionUniversitiesByDirectionMap).entrySet()) {
            for (Map.Entry<String, List<Student>> entry1 : entry.getValue().entrySet()) {
                map.put(entry1.getKey(), String.valueOf(entry1.getValue().size()));
            }
            list.addAll(reportList.get(entry.getKey()));
            list.add(new HashMap<>() {{
                putAll(map);
            }});
            reportList.put(entry.getKey(), new ArrayList<>() {{
                addAll(list);
            }});
            list.clear();
            map.clear();
        }
        //Расчетное кол-во стипендий
        for (Map.Entry<String, Map<String, Long>> entry : getCountScholarshipsByDirectionAndUniversities(semester, year).entrySet()) {
            for (Map.Entry<String, Long> entry1 : entry.getValue().entrySet()) {
                map.put(entry1.getKey(), String.valueOf(entry1.getValue()));
            }
            list.addAll(reportList.get(entry.getKey()));
            list.add(new HashMap<>() {{
                putAll(map);
            }});
            reportList.put(entry.getKey(), new ArrayList<>() {{
                addAll(list);
            }});
            list.clear();
            map.clear();
        }
        return reportList;
    }
}
