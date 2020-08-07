package com.oneightwo.scholarship_distribution.security.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled = true;
    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired = true;
    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired = true;
    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private List<Role> roles;

}
