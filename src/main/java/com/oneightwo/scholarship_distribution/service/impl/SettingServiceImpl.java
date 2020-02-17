package com.oneightwo.scholarship_distribution.service.impl;

import com.oneightwo.scholarship_distribution.constants.Constants;
import com.oneightwo.scholarship_distribution.repository.SettingRepository;
import com.oneightwo.scholarship_distribution.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingRepository settingRepository;

    @Override
    public boolean isActiveRegistration() {
        return settingRepository.findById(Constants.ACTIVE_REGISTRATION_ID).orElse(null).isActiveRegistration();
    }
}
