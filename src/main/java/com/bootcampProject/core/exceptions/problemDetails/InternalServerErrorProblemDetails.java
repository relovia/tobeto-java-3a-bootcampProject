package com.bootcampProject.core.exceptions.problemDetails;

import org.springframework.http.HttpStatus;

public class InternalServerErrorProblemDetails extends ProblemDetails {
    public InternalServerErrorProblemDetails() {
        setTitle("Internal server error");
        setType("https://www.youtube.com");
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
}
