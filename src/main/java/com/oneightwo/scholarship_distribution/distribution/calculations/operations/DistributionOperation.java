package com.oneightwo.scholarship_distribution.distribution.calculations.operations;

import com.oneightwo.scholarship_distribution.distribution.calculations.models.DistributionUnit;

import java.util.Map;

public interface DistributionOperation {

    Map<Long, Integer> execute(DistributionUnit distributionUnit, int quota);
}
