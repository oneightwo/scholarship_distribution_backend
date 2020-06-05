package com.oneightwo.scholarship_distribution.repository;

import com.oneightwo.scholarship_distribution.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SettingsRepository extends JpaRepository<Settings, BigInteger> {
}
