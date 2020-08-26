package com.oneightwo.scholarship_distribution.files_storage.services;

import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

public interface FileStorageService {

    /**
     * загрузка файла
     * @param file файл
     * @param name новое имя файла
     * @throws CoreException
     */
    void upload(MultipartFile file, String name) throws CoreException;

    /**
     * загрузка файла
     * @param file файл
     * @throws CoreException
     */
    void upload(MultipartFile file) throws CoreException;

    /**
     * выгрузка файла
     * @param year год
     * @param semester семестр
     * @param name название файла
     * @return найденный файл
     * @throws CoreException
     */
    ByteArrayOutputStream download(String year, Semester semester, String name) throws CoreException;
}
