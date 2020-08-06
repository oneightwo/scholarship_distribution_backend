package com.oneightwo.scholarship_distribution.security.repositories;

import com.oneightwo.scholarship_distribution.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, BigInteger> {

    Optional<User> findByUsername(String username);
}
