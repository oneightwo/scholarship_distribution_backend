package com.oneightwo.scholarship_distribution.constants;

import java.math.BigInteger;

public interface Constants {
    Integer[] VK = new Integer[]{3, 1, 2, 3, 1, 2, 3, 3, 2, 2, 2, 1, 1, 2, 1};
    Integer[] UR = new Integer[]{0, 6, 12, 25, 52, 100};
    Double[] KEF = new Double[]{0.0, 0.022662, 0.05589, 0.137837};
    Double MINIMUM_PERCENTAGE_BORDER = 0.25;
    String PASSED = "PASSED";
    String NOT_PASSED = "NOT_PASSED";
    Long NUMBER_SCHOLARSHIPS = 108L;
    String UPLOAD = "/upload";
    String PDF_EXTENSION = ".pdf";

    //-----Setting-----
    BigInteger ACTIVE_REGISTRATION_ID = BigInteger.valueOf(1L);
}
