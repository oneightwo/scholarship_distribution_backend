package com.oneightwo.scholarship_distribution.service;

import com.oneightwo.scholarship_distribution.constants.Semester;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

public interface FileService {

    boolean upload(MultipartFile file, String name);

    ByteArrayResource download(int year, Semester semester, BigInteger id);
}
