package com.oneightwo.scholarship_distribution.service.impl;

import com.oneightwo.scholarship_distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
public class FileServiceImpl implements FileService {

    private Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    private String getNameFolderSemester() {
        LocalDate date = LocalDate.now();
        if (Semester.AUTUMN.getMonths().contains(date.getMonthValue())) {
            return Semester.AUTUMN.getName();
        } else {
            return Semester.SPRING.getName();
        }
    }

    private String getDir() {
        return new File("").getAbsolutePath();
    }

    private String getNameFolderYear() {
        return String.valueOf(LocalDate.now().getYear());
    }

    @Override
    public boolean upload(MultipartFile file, String name) {
        if (!file.isEmpty()) {
            String path = getDir() + "/" + "upload";
            if (!new File(path).exists()) {
                new File(path).mkdir();
            }
            path += "/" + getNameFolderYear();
            if (!new File(path).exists()) {
                new File(path).mkdir();
            }
            path += "/" + getNameFolderSemester();
            if (!new File(path).exists()) {
                new File(path).mkdir();
            }
            try {
                String fileName = file.getOriginalFilename();
                log.info(file.getOriginalFilename());
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + "/" + name + ".jpg")));
                stream.write(bytes);
                stream.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public ByteArrayResource download(int year, Semester semester, BigInteger id) {
//        try {
//            File file = new File("D:/Java Spring/scholarship_distribution/2020/Весна/photo_2019-06-24_17-40-08.jpg");
//            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
//            return inputStreamResource;
//        } catch (Exception e) {
//            return null;
//        }
        try {
            File file = new File(getDir() + "/upload/" +
                    String.valueOf(year) + "/" + semester.getName() + "/" + String.valueOf(id) + ".jpg");
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            return resource;
        } catch (Exception e) {
            return null;
        }
    }
}
