package com.oneightwo.scholarship_distribution.data_view.models;

import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import lombok.*;

//@NoArgsConstructor
@AllArgsConstructor
@Getter
//@Setter
@ToString
public class StudentData {

    private final StudentDTO studentDTO;

    private final AdditionalInformation additionalInformation;

}
