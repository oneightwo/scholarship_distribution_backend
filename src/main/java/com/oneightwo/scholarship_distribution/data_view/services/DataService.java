package com.oneightwo.scholarship_distribution.data_view.services;

import com.oneightwo.scholarship_distribution.data_view.models.FormData;

public interface DataService {

    FormData execute();

    FormData execute(Long studentId);
}
