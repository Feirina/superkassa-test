package com.fei.superkassatest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ApiError handle(final Throwable e) {
        return new ApiError.ApiErrorBuilder()
                .errors(List.of(e.getClass().getName()))
                .message(e.getLocalizedMessage())
                .reason("Throwable exception")
                .status(HttpStatus.I_AM_A_TEAPOT)
                .build();
    }
}
