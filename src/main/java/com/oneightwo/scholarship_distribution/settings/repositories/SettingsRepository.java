package com.oneightwo.scholarship_distribution.settings.repositories;

import com.oneightwo.scholarship_distribution.settings.models.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
