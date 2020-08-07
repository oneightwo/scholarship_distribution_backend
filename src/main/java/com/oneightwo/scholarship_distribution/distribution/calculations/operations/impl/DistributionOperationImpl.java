package com.oneightwo.scholarship_distribution.distribution.calculations.operations.impl;

import com.oneightwo.scholarship_distribution.distribution.calculations.models.DistributionUnit;
import com.oneightwo.scholarship_distribution.distribution.calculations.operations.DistributionOperation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DistributionOperationImpl implements DistributionOperation {

    private DistributionUnit distributionUnit;
    private int quota;
    private Map<Long, Integer> result;

    @Override
    public Map<Long, Integer> execute(DistributionUnit distributionUnit, int quota) {
        result = new HashMap<>();
        this.distributionUnit = new DistributionUnit(distributionUnit);
        this.quota = quota;

        while (this.quota != 0) {
            if (isEnoughFreeQuota()) {
                distributionOfMostPartOfQuota();
            } else {
                if (this.quota > 0) {
                    distributionOfSmallerPartOfQuotaIfFreeGreaterThanZero();
                } else {
                    distributionOfSmallerPartOfQuotaIfFreeLessThanZero();
                }
            }
            clearIterationData();
        }
        return result;
    }

    private boolean isEnoughFreeQuota() {
        if (quota < 0) {
            return false;
        } else {
            return !distributionUnit.getRelationshipRatings().values()
                    .stream()
                    .allMatch(rating -> Math.round(rating * quota) == 0);
        }
    }

    private void distributionOfSmallerPartOfQuotaIfFreeGreaterThanZero() {
        increaseResult(getKeyOfHighestRating(), 1);
    }

    private void distributionOfSmallerPartOfQuotaIfFreeLessThanZero() {
        increaseResult(getKeyOfSmallerRating(), -1);
    }

    private Long getKeyOfHighestRating() {
        var maxOfKey = -1L;
        var maxRating = Double.MIN_VALUE;
        for (Map.Entry<Long, Double> entry : distributionUnit.getRelationshipRatings().entrySet()) {
            Long key = entry.getKey();
            Double rating = entry.getValue();
            if (rating > maxRating) {
                maxOfKey = key;
                maxRating = rating;
            }
        }
        return maxOfKey;
    }

    private Long getKeyOfSmallerRating() {
        var minOfKey = -1L;
        var minRating = Double.MAX_VALUE;
        for (Map.Entry<Long, Double> entry : distributionUnit.getRelationshipRatings().entrySet()) {
            Long key = entry.getKey();
            Double rating = entry.getValue();
            if (rating < minRating) {
                minOfKey = key;
                minRating = rating;
            }
        }
        return minOfKey;
    }

    private void distributionOfMostPartOfQuota() {
        int quota = this.quota;
        for (Map.Entry<Long, Double> entry : distributionUnit.getRelationshipRatings().entrySet()) {
            Long key = entry.getKey();
            Double rating = entry.getValue();
            increaseResult(key, (int) Math.round(rating * quota));
        }
    }

    private void increaseResult(Long key, int quantity) {
        quota -= quantity;
        result.merge(key, quantity, Integer::sum);
    }

    private void clearIterationData() {
        eliminationFromExceedingSetQuantity();
        removeFilledItemsFromProcess();
    }

    private void eliminationFromExceedingSetQuantity() {
        result.forEach((key, assignedAmount) -> {
            var numberOfPlace = distributionUnit.getStudentsOrDefaultById(key).size();
            if (numberOfPlace != 0 && assignedAmount > numberOfPlace) {
                var difference = numberOfPlace - assignedAmount;
                increaseResult(key, difference);
            }
        });
    }

    private void removeFilledItemsFromProcess() {
        result.forEach((key, assignedAmount) -> {
            var numberOfPlace = distributionUnit.getStudentsOrDefaultById(key).size();
            if (numberOfPlace != 0 && assignedAmount == numberOfPlace) {
                distributionUnit.deleteById(key);
            }
        });
    }
}
