package com.oneightwo.scholarship_distribution.data_view.models;

import com.oneightwo.scholarship_distribution.courses.models.CourseDTO;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirectionDTO;
import com.oneightwo.scholarship_distribution.universities.models.UniversityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.TreeSet;

@NoArgsConstructor
@Getter
@Setter
public class AdditionalInformation {

    private TreeSet<UniversityDTO> universities = new TreeSet<>();

    private TreeSet<CourseDTO> courses = new TreeSet<>();

    private TreeSet<ScienceDirectionDTO> scienceDirections = new TreeSet<>();
}
