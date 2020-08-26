//package com.oneightwo.scholarship_distribution.core.handlers;
//
//import com.oneightwo.scholarship_distribution.core.exceptions.*;
//import com.oneightwo.scholarship_distribution.core.models.ResponseErrorView;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//@Slf4j
//public class RuntimeExceptionHandler {
//
//    @ExceptionHandler(FileNotFoundException.class)
//    protected ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException ex) {
//        ResponseErrorView responseErrorView = new ResponseErrorView(HttpStatus.NOT_FOUND, "Файл не найден", ex.getMessage());
//        return new ResponseEntity<>(responseErrorView, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(InvalidFileFormatException.class)
//    protected ResponseEntity<Object> handleInvalidFileFormatException(InvalidFileFormatException ex) {
//        ResponseErrorView exceptionView = new ResponseErrorView(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Неверный формат файла", ex.getMessage());
//        return new ResponseEntity<>(exceptionView, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//    }
//
//    @ExceptionHandler(NotEnoughStudentsException.class)
//    protected ResponseEntity<Object> handleNotEnoughStudentsException(NotEnoughStudentsException ex) {
//        ResponseErrorView exceptionView = new ResponseErrorView(HttpStatus.CONFLICT, "Отсутствует необходимое количество студентов для распрделения стипендий", ex.getMessage());
//        return new ResponseEntity<>(exceptionView, HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(RegistrationIsNotActiveException.class)
//    protected ResponseEntity<Object> handleRegistrationIsNotActiveException(RegistrationIsNotActiveException ex) {
//        ResponseErrorView exceptionView = new ResponseErrorView(HttpStatus.CONFLICT, "Регистрация в данный момент не активна", ex.getMessage());
//        return new ResponseEntity<>(exceptionView, HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(SemesterNotFoundException.class)
//    protected ResponseEntity<Object> handleSemesterNotFoundException(SemesterNotFoundException ex) {
//        ResponseErrorView exceptionView = new ResponseErrorView(HttpStatus.BAD_REQUEST, "Такого семестра не существует", ex.getMessage());
//        return new ResponseEntity<>(exceptionView, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(StudentNotFoundException.class)
//    protected ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex) {
//        ResponseErrorView exceptionView = new ResponseErrorView(HttpStatus.NOT_FOUND, "Студент не найден", ex.getMessage());
//        return new ResponseEntity<>(exceptionView, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(CoreException.class)
//    protected ResponseEntity<Object> handleCoreException(CoreException ex) {
//        ResponseErrorView exceptionView = new ResponseErrorView(HttpStatus.INTERNAL_SERVER_ERROR, "CoreException", ex.getMessage());
//        return new ResponseEntity<>(exceptionView, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}
