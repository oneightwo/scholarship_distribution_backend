package com.oneightwo.scholarship_distribution.science_directions.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "science_directions")
public class ScienceDirection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "science_direction_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean deleted;

    //    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "scienceDirection")
//    private List<Student> students;
}
