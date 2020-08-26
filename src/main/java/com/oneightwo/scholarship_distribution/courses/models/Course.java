package com.oneightwo.scholarship_distribution.courses.models;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public enum Course {

    FIRST(1),
    SECOND(2),
    THIRD(3);

    private final int value;

    Course(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Course getByValue(int value) {
        for (Course v : Course.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        throw new NoSuchElementException("Не существует семестр: " + value);
    }

//    public static List<Course> getByDate(LocalDate date) {
//        if (date.getMonth())
//    }
}
