package com.employee.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmptyReportException extends ApplicationException {

    /**
     * Constructs a new EmptyReportException exception
     */
    public EmptyReportException() {
        super(String.format("Report data is empty. Please check passed team name and try again."));
    }
}
