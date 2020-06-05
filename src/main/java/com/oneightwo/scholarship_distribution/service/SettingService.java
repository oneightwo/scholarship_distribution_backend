package com.oneightwo.scholarship_distribution.service;

import com.oneightwo.scholarship_distribution.model.Settings;

import java.util.List;

public interface SettingService {

    boolean isActiveRegistration();

    List<Settings> getAll();

    Settings changeSettings(Settings settings);

}
