package com.oneightwo.scholarship_distribution;

import com.oneightwo.scholarship_distribution.core.helpers.TransformationHelper;
import com.oneightwo.scholarship_distribution.distribution.calculations.services.impl.StudentsInDirectionsServiceImpl;
import com.oneightwo.scholarship_distribution.distribution.calculations.services.impl.StudentsInDirectionsAndUniversitiesServiceImpl;
import com.oneightwo.scholarship_distribution.data_view.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByScienceDirectionsService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByUniversitiesService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsAndUniversitiesReportService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsReportService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScholarshipDistributionApplicationTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScienceDirectionService scienceDirectionService;

    @Autowired
    private StudentsInDirectionsServiceImpl studentsInDirectionsServiceImpl;
    @Autowired
    private StudentsInDirectionsAndUniversitiesServiceImpl studentsInDirectionsAndUniversitiesServiceImpl;
    @Autowired
    private ScholarshipDistributionByDirectionsReportService scholarshipDistributionByDirectionsReportService;
    @Autowired
    private ScholarshipDistributionByDirectionsAndUniversitiesReportService scholarshipDistributionByDirectionsAndUniversitiesReportService;

    @Test
    public void test() {
//        System.out.println(scienceDirectionService.getAll());
//        test1();
//        test2();
//        test3();
        generateTestData(108);
//        testDistributionInDirections();
//        testDistributionInDirectionAndUniversities();

    }

    @Autowired
    private ReportByScienceDirectionsService reportByScienceDirectionsService;

    public void test3() {
        System.out.println(reportByScienceDirectionsService.execute(Semester.SPRING, 2020));
    }

    @Autowired
    private ReportByUniversitiesService reportByUniversitiesService;

    public void test4() {
        System.out.println(reportByUniversitiesService.execute(Semester.SPRING, 2020));
    }

    public void test1() {
        scholarshipDistributionByDirectionsReportService.setStudents(studentService.getAll());
        System.out.println("1 " + scholarshipDistributionByDirectionsReportService.getAssignedNumberScholarships());
        System.out.println("2 " + scholarshipDistributionByDirectionsReportService.getAverageRatings());
        System.out.println("3 " + scholarshipDistributionByDirectionsReportService.getMinimalRating());
        System.out.println("4 " + scholarshipDistributionByDirectionsReportService.getNumberApplications());
        System.out.println("5 " + scholarshipDistributionByDirectionsReportService.getNumberExcludedApplications());
        System.out.println("6 " + scholarshipDistributionByDirectionsReportService.getNumberPassedApplications());
    }

    public void test2() {
        scholarshipDistributionByDirectionsAndUniversitiesReportService.setStudents(studentService.getAll());
        System.out.println(scholarshipDistributionByDirectionsAndUniversitiesReportService.getAllApplicationsSubmittedToUniversities());
        System.out.println(scholarshipDistributionByDirectionsAndUniversitiesReportService.getExcludedApplicationsSubmittedToUniversities());
        System.out.println(scholarshipDistributionByDirectionsAndUniversitiesReportService.getPassedApplicationsSubmittedToUniversities());
        System.out.println(scholarshipDistributionByDirectionsAndUniversitiesReportService.getDataDistributionByUniversitiesInDirections());
        System.out.println(scholarshipDistributionByDirectionsAndUniversitiesReportService.getDataDistributionByUniversitiesAndDirections());
    }

    public void generateTestData(int quantity) {
        for (int i = 0; i < quantity; i++) {
            studentService.save(TransformationHelper.dtoToObject(new StudentDTO(
                    generateWord(),
                    generateWord(),
                    generateWord(),
                    (long) getRandomNumber(1, 3),
                    generateWord(),
                    getRandomNumber(1, 3),
                    generateWord() + "@" + generateWord() + ".com",
                    "88005553535",
                    (long) getRandomNumber(1, 3),
                    getRandomString(),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    getRandomNumber(0, 5),
                    true
            )));
        }
    }

    public int getRandomNumber(int a, int b) {
        return (int) (Math.random() * ((b - a) + 1)) + a;
    }

    private String generateWord() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lettersLower = letters.toLowerCase();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < getRandomNumber(3, 10); i++) {
            word.append(i == 0 ? letters.charAt(getRandomNumber(0, letters.length() - 1)) : lettersLower.charAt(getRandomNumber(0, letters.length() - 1)));
        }
        return word.toString();
    }

    public String getRandomString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < getRandomNumber(3, 15); i++) {
            str.append(generateWord()).append(" ");
        }
        return str.toString();
    }

    public void testDistributionInDirections() {
        studentsInDirectionsServiceImpl.setStudents(studentService.getAll());
        System.out.println(studentsInDirectionsServiceImpl.getAssignedNumberScholarships());
    }

    public void testDistributionInDirectionAndUniversities() {
        studentsInDirectionsAndUniversitiesServiceImpl.setStudents(studentService.getAll());
        System.out.println(studentsInDirectionsAndUniversitiesServiceImpl.getAssignedNumberScholarships());
    }
}
