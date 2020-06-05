package com.oneightwo.scholarship_distribution.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ErrorView {
    private HttpStatus status;
    private String message;
    private String debugMessage;
}