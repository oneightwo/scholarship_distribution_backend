package com.oneightwo.scholarship_distribution.distribution.calculations.services;

import org.springframework.stereotype.Service;

import static com.oneightwo.scholarship_distribution.distribution.constants.Constants.*;

@Service
public class CalculateRatingService {

    public static class Criteria {
        int c1;
        int c2;
        int c3;
        int c4;
        int c5;
        int c6;
        int c7;
        int c8;
        int c9;
        int c10;
        int c11;
        int c12;
        int c13;
        int c14;
        int c15;

        public Criteria(int c1, int c2, int c3, int c4, int c5, int c6, int c7, int c8, int c9, int c10, int c11, int c12, int c13, int c14, int c15) {
            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
            this.c4 = c4;
            this.c5 = c5;
            this.c6 = c6;
            this.c7 = c7;
            this.c8 = c8;
            this.c9 = c9;
            this.c10 = c10;
            this.c11 = c11;
            this.c12 = c12;
            this.c13 = c13;
            this.c14 = c14;
            this.c15 = c15;
        }

        public int[] getCriteria() {
            return new int[]{c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15};
        }
    }

    public int calculate(Criteria criteria) {
        int[] c = criteria.getCriteria();
        double rating = 0.0;
        for (int i = 0; i < c.length; i++) {
            rating += KEF[VK[i]] * UR[c[i]];
        }
        return (int) Math.round(rating);
    }

}
