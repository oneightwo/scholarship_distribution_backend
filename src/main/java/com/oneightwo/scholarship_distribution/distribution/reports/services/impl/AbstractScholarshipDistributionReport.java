package com.oneightwo.scholarship_distribution.distribution.reports.services.impl;

import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;

import java.util.List;

public abstract class AbstractScholarshipDistributionReport {

    protected  List<ScienceDirection> scienceDirections;

    public AbstractScholarshipDistributionReport(ScienceDirectionService scienceDirectionService) {
        this.scienceDirections = scienceDirectionService.getAll();
    }

    protected String getNameScienceDirectionById(Long id) {
        for (ScienceDirection scienceDirection : scienceDirections) {
            if (scienceDirection.getId().equals(id)) {
                return scienceDirection.getName();
            }
        }
        return "";
    }

//    protected <T> String getNameById(List<T> list, Long id) {
//        Object object = list.get(0);
//        if (object instanceof ScienceDirection) {
//            for (ScienceDirection scienceDirection : (List<ScienceDirection>) list) {
//                if (scienceDirection.getId().equals(id)) {
//                    return scienceDirection.getName();
//                }
//            }
//        } else if (object instanceof University) {
//            for (University university : (List<University>) list) {
//                if (university.getId().equals(id)) {
//                    return university.getName();
//                }
//            }
//        }
//        return "";
//    }
}
