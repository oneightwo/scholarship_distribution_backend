package com.oneightwo.scholarship_distribution.security.repositories;

import com.oneightwo.scholarship_distribution.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RoleRepository extends JpaRepository<Role, BigInteger> {

}
