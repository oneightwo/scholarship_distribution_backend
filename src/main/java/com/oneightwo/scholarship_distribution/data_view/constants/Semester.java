package com.oneightwo.scholarship_distribution.data_view.constants;

import com.oneightwo.scholarship_distribution.core.exceptions.SemesterNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public enum Semester {
    AUTUMN(List.of(9, 10, 11, 12)), SPRING(List.of(1, 2, 3, 4, 5, 6, 7, 8));

    private final List<Integer> months;

    private Semester(List<Integer> months) {
        this.months = months;
    }

    public List<Integer> getMonths() {
        return months;
    }

    public static Semester getSemesterByMonth(int month) {
        for (Semester semester : Semester.values()) {
            if (semester.getMonths().contains(month)) {
                return semester;
            }
        }
        return null;
    }

    public static Semester getSemesterByDate(LocalDate localDate) {
        for (Semester semester : Semester.values()) {
            if (semester.getMonths().contains(localDate.getMonthValue())) {
                return semester;
            }
        }
        throw new NoSuchElementException("Данного месяца не существует");
    }

    public static List<Integer> getMonths(String month) throws SemesterNotFoundException {
        for (Semester semester : Semester.values()) {
            if (semester.name().equals(month.toUpperCase())) {
                return semester.getMonths();
            }
        }
        throw new SemesterNotFoundException();
    }

    public static Semester getSemesterByName(String name) throws SemesterNotFoundException {
        try {
            return Semester.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new SemesterNotFoundException();
        }
    }

//    public static List<> getMonthsByName(String name) throws SemesterNotFoundException {
//        try {
//            return Semester.valueOf(name.toUpperCase());
//        } catch (IllegalArgumentException ex) {
//            throw new SemesterNotFoundException();
//        }
//    }

}
