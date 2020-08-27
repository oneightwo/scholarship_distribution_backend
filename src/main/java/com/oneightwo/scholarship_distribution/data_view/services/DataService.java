package com.oneightwo.scholarship_distribution.data_view.services;

import com.oneightwo.scholarship_distribution.data_view.models.StudentData;

public interface DataService<T, I> {

    T execute(I i);

}
