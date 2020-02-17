package com.oneightwo.scholarship_distribution.tools;

import java.util.Collection;

public class Tools {

    public static Long sumLong(Collection<Long> list) {
        Long val = 0L;
        for (Long v : list) {
            val += v;
        }
        return val;
    }
    public static Double sumDouble(Collection<Double> list) {
        Double val = 0.0;
        for (Double v : list) {
            val += v;
        }
        return val;
    }
}
