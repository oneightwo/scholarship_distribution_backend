package com.oneightwo.scholarship_distribution.distribution.computing.operations;

import com.oneightwo.scholarship_distribution.distribution.computing.models.DistributionUnit;

import java.util.Map;

public interface DistributionOperation {

    Map<Long, Integer> execute(DistributionUnit distributionUnit, int quota);
}
