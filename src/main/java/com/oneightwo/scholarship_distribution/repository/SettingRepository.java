package com.oneightwo.scholarship_distribution.repository;

import com.oneightwo.scholarship_distribution.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SettingRepository extends JpaRepository<Setting, BigInteger> {
}
