package com.oneightwo.scholarship_distribution.distribution;

import com.oneightwo.scholarship_distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.model.Student;
import com.oneightwo.scholarship_distribution.service.impl.ScienceDirectionServiceImpl;
import com.oneightwo.scholarship_distribution.service.impl.StudentServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

import static com.oneightwo.scholarship_distribution.constants.Constants.*;

public abstract class DistributionService {

    /**
     * расчет рейтинга по критериям
     * @param criteria массив критериев длиной 15, диапазоном 0-5
     * @return рейтинг
     */
    public int calculationRating(int[] criteria) {
        double rating = 0.0;
        for (int i = 0; i < criteria.length; i++) {
            rating += KEF[VK[i]] * UR[criteria[i]];
        }
        return (int) Math.round(rating);
    }

    /**
     * распределение стипендий внутри направлений по университемам
     * @param semester семестр распределения
     * @param year год распределения
     * @return Map<Направление, Map<Университет, Кол-во стипендий>>
     */
    public abstract Map<String, Map<String, Long>> getCountScholarshipsByDirectionAndUniversities(Semester semester, int year);

    /**
     * получение студентов победителей
     * @param semester семестр
     * @param year год
     * @return Map<Универсиет, Студенты>
     */
    public abstract Map<String, List<Student>> getWinnerStudents(Semester semester, int year);

    /**
     * получение не прошедших отбор студентов
     * @param semester семестр
     * @param year год
     * @return Map<Универсиет, Студенты>
     */
    public abstract Map<String, List<Student>> getLoserStudents(Semester semester, int year);

    /**
     * формирование отчета по направлениям
     * @param semester семестр
     * @param year год
     * @return List<Map<Направление, Значение>>
     */
    public abstract List<Map<String, String>> getReportByDirection(Semester semester, int year);

    /**
     * формирование отчета внутри напрвления по университетам
     * @param semester семестер
     * @param year год
     * @return Map<Направление, List<Map<Университет, Значение>>>
     */
    public abstract Map<String, List<Map<String, String>>> getReportByDirectionAndUniversities(Semester semester, int year);

}
