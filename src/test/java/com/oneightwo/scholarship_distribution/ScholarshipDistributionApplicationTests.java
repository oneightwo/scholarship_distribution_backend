package com.oneightwo.scholarship_distribution;

import com.oneightwo.scholarship_distribution.model.Course;
import com.oneightwo.scholarship_distribution.model.ScienceDirection;
import com.oneightwo.scholarship_distribution.model.Student;
import com.oneightwo.scholarship_distribution.model.University;
import com.oneightwo.scholarship_distribution.service.CourseService;
import com.oneightwo.scholarship_distribution.service.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.service.StudentService;
import com.oneightwo.scholarship_distribution.service.UniversityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScholarshipDistributionApplicationTests {

//    @Test

//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private UniversityService universityService;
//
//    @Autowired
//    private ScienceDirectionService scienceDirectionService;
//
//    @Autowired
//    private CourseService courseService;
//
//    @Test
//    public void contextLoads() {
//    }
//
//    public int getRandomNumber(int a, int b) {
//        return a + (int) (Math.random() * b);
//    }
//
//    private String generateWord() {
//        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String lettersLower = letters.toLowerCase();
//        StringBuilder word = new StringBuilder();
//        for (int i = 0; i < getRandomNumber(3, 10); i++) {
//            word.append(i == 0 ? letters.charAt(getRandomNumber(0, letters.length() - 1)) : lettersLower.charAt(getRandomNumber(0, letters.length() - 1)));
//        }
//        return word.toString();
//    }
//
//    public String getRandomString() {
//        StringBuilder str = new StringBuilder();
//        for (int i = 0; i < getRandomNumber(3, 15); i++) {
//            str.append(generateWord()).append(" ");
//        }
//        return str.toString();
//    }
//
//    @Test
//    public void generateTestData() {
//        for (int i = 0; i < 120; i++) {
//            studentService.save(new Student(
//                    generateWord(),
//                    generateWord(),
//                    generateWord(),
//                    universityService.getById(BigInteger.valueOf(getRandomNumber(1, 17))).orElse(new University()),
//                    generateWord(),
//                    courseService.getById(BigInteger.valueOf(getRandomNumber(1, 3))).orElse(new Course()),
//                    generateWord() + "@" + generateWord() + ".com",
//                    "88005553535",
//                    scienceDirectionService.getById(BigInteger.valueOf(getRandomNumber(1, 3))).orElse(new ScienceDirection()),
//                    getRandomString(),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    getRandomNumber(0, 5),
//                    true
//			));
//        }
//    }
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Test
//    public void testC() {
//        System.out.println(bCryptPasswordEncoder.encode("devglan-secret"));
//    }
}
