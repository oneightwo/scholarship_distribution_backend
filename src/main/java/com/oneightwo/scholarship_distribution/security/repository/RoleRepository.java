package com.oneightwo.scholarship_distribution.security.repository;

import com.oneightwo.scholarship_distribution.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RoleRepository extends JpaRepository<Role, BigInteger> {

}
