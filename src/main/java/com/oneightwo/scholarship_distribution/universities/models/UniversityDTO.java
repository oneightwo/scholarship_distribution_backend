package com.oneightwo.scholarship_distribution.universities.models;

import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UniversityDTO implements Comparable<UniversityDTO> {

    @JsonProperty("university_id")
    private Long id;
    private String name;
    private String abbreviation;
    private boolean deleted;

    @Override
    public int compareTo(@NotNull UniversityDTO o) {
        return this.id.compareTo(o.id);
    }
}
