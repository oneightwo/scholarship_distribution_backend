package com.oneightwo.scholarship_distribution.science_directions.models;

import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ScienceDirectionDTO {

    @JsonProperty("science_direction_id")
    private Long id;
    private String name;
    private boolean deleted;
}
