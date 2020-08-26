package com.oneightwo.scholarship_distribution.data_view.services.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.core.helpers.TransformationHelper;
import com.oneightwo.scholarship_distribution.data_view.models.StudentRegistration;
import com.oneightwo.scholarship_distribution.data_view.services.StudentRegistrationService;
import com.oneightwo.scholarship_distribution.files_storage.services.FileStorageService;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {


    private final StudentService studentService;
    private final FileStorageService fileStorageService;

    public StudentRegistrationServiceImpl(StudentService studentService, FileStorageService fileStorageService) {
        this.studentService = studentService;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public void save(StudentRegistration studentRegistration) {
        studentService.save(TransformationHelper.dtoToObject(studentRegistration.getStudent()));
//        try {
//            fileStorageService.upload(studentRegistration.getMultipartFile());
//        } catch (CoreException e) {
//            log.error("upload file e", e.fillInStackTrace());
//        }
    }
}
