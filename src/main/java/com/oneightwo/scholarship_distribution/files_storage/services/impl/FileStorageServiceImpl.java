package com.oneightwo.scholarship_distribution.files_storage.services.impl;

import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.core.exceptions.FileNotFoundException;
import com.oneightwo.scholarship_distribution.core.exceptions.InvalidFileFormatException;
import com.oneightwo.scholarship_distribution.files_storage.constants.Constants;
import com.oneightwo.scholarship_distribution.files_storage.services.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Logger log = LoggerFactory.getLogger(FileStorageServiceImpl.class);

    /**
     * получение названия папки семестра
     * @return название папки равна текущему семестру (AUTUMN или SPRING)
     */
    private String getNameFolderSemester() {
        LocalDate date = LocalDate.now();
        if (Semester.AUTUMN.getMonths().contains(date.getMonthValue())) {
            return "/" + Semester.AUTUMN;
        } else {
            return "/" + Semester.SPRING;
        }
    }

    /**
     * получение навзвание папки года
     * @return навзание папки равная текущему году
     */
    private String getNameFolderYear() {
        return "/" + LocalDate.now().getYear();
    }

    /**
     * получение абсолютного пути + /upload
     * @return абсолютный путь /upload
     */
    private String getDir() {
        return new File("").getAbsolutePath() + Constants.UPLOAD;
    }

    /**
     * получение расширения файла
     * @param file файл для проверки
     * @return расширение (.extension)
     * @throws CoreException неверное расширение файла
     */
    private String getFileExtension(MultipartFile file) throws CoreException {
        String name = file.getOriginalFilename();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            throw new InvalidFileFormatException();
        }
        return name.substring(lastIndexOf);
    }

    /**
     * получение текущего пути (текущий семестр, текущий год) для сохранения файла
     * @return текущий путь (абсолютный путь/upload/год/семестр)
     */
    private Path getCurrentPath() {
        StringBuilder pathStr = new StringBuilder(getDir());
        pathStr.append(getNameFolderYear());
        pathStr.append(getNameFolderSemester());
        if (Files.exists(Paths.get(pathStr.toString()))) {
            return Paths.get(pathStr.toString());
        } else {
            File file = new File(pathStr.toString());
            file.mkdirs();
            return file.toPath();
        }
    }

    /**
     * проверка файла
     * @param file файл
     * @throws CoreException файл не найден или неверное расширение файла
     */
    private void checkingFile(MultipartFile file) throws CoreException {
        if (file.isEmpty()) {
            throw new FileNotFoundException();
        }
        if (!getFileExtension(file).equals(Constants.PDF_EXTENSION)) {
            throw new InvalidFileFormatException();
        }
    }

    @Override
    public void upload(MultipartFile file) throws CoreException {
        checkingFile(file);
        try {
            file.transferTo(new File(getCurrentPath() + "/" + file.getOriginalFilename()));
        } catch (IOException e) {
            throw new CoreException();
        }
    }

    @Override
    public void upload(MultipartFile file, String name) throws CoreException {
        checkingFile(file);
        try {
            file.transferTo(new File(getCurrentPath() + "/" + name + getFileExtension(file)));
        } catch (IOException e) {
            throw new CoreException();
        }
    }

    @Override
    public ByteArrayOutputStream download(String year, Semester semester, String name) throws CoreException {
        try {
            File file = new File(getDir() + "/" + year + "/" + semester + "/" + name + Constants.PDF_EXTENSION);
            InputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.writeBytes(inputStream.readAllBytes());
            return byteArrayOutputStream;
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }
}
