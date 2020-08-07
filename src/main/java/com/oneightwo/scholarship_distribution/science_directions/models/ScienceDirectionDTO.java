package com.oneightwo.scholarship_distribution.science_directions.models;

import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ScienceDirectionDTO implements Comparable<ScienceDirectionDTO> {

    @JsonProperty("science_direction_id")
    private Long id;
    private String name;
    private boolean deleted;

    @Override
    public int compareTo(@NotNull ScienceDirectionDTO o) {
        return this.id.compareTo(o.id);
    }
}
