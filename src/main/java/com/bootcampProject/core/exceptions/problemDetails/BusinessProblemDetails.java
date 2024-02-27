package com.bootcampProject.core.exceptions.problemDetails;

import org.springframework.http.HttpStatus;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails() {
        setTitle("Business rule violation");
        setType("https://www.youtube.com");
        setStatus(HttpStatus.BAD_REQUEST.toString());
    }
}
