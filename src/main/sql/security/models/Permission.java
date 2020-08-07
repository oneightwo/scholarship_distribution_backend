package com.oneightwo.scholarship_distribution.security.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;
    @Column(nullable = false)
    private String name;
}
