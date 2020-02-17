package com.oneightwo.scholarship_distribution.constants;

import java.util.List;

public enum Semester {
    AUTUMN(List.of(9, 10, 11, 12), "Осень"), SPRING(List.of(1, 2, 3, 4, 5, 6, 7, 8), "Весна");

    private List<Integer> months;
    private String name;

    private Semester(List<Integer> months, String name) {
        this.months = months;
        this.name = name;
    }

    public List<Integer> getMonths() {
        return months;
    }

    public String getName() {
        return name;
    }
}
