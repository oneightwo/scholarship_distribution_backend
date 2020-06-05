package com.oneightwo.scholarship_distribution.dto;

import java.math.BigInteger;

public class BasicInformation {

    BigInteger countParticipants;
    BigInteger countParticipantsByTechnicalDirection;
    BigInteger countParticipantsByScienceDirection;
    BigInteger countParticipantsBySocialDirection;

    public BasicInformation(BigInteger countParticipants,
                            BigInteger countParticipantsByTechnicalDirection,
                            BigInteger countParticipantsByScienceDirection,
                            BigInteger countParticipantsBySocialDirection) {
        this.countParticipants = countParticipants;
        this.countParticipantsByTechnicalDirection = countParticipantsByTechnicalDirection;
        this.countParticipantsByScienceDirection = countParticipantsByScienceDirection;
        this.countParticipantsBySocialDirection = countParticipantsBySocialDirection;
    }
}
