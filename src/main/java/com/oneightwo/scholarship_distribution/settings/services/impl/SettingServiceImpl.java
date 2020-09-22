package com.oneightwo.scholarship_distribution.settings.services.impl;

import com.oneightwo.scholarship_distribution.data_view.constants.Constants;
import com.oneightwo.scholarship_distribution.settings.models.Settings;
import com.oneightwo.scholarship_distribution.settings.repositories.SettingsRepository;
import com.oneightwo.scholarship_distribution.settings.services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public boolean isActiveRegistration() {
        return settingsRepository.findById(Constants.ACTIVE_REGISTRATION_ID).orElse(null).isActiveRegistration();
    }

    @Override
    public List<Settings> getAll() {
        return settingsRepository.findAll();
    }

    @Override
    public Settings changeSettings(Settings settings) {
        return settingsRepository.save(settings);
    }
}
