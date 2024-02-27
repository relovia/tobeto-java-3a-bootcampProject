package com.bootcampProject.core.exceptions.problemDetails;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails {
    private Map<String, String> errors;

    public ValidationProblemDetails() {
        setTitle("Validaiton rule violation");
        setType("https://www.youtube.com");
        setStatus(HttpStatus.BAD_REQUEST.toString());
    }
}
