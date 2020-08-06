package com.oneightwo.scholarship_distribution.settings.services;

import com.oneightwo.scholarship_distribution.settings.models.Settings;

import java.util.List;

public interface SettingService {

    boolean isActiveRegistration();

    List<Settings> getAll();

    Settings changeSettings(Settings settings);

}
