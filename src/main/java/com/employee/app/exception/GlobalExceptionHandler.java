package com.employee.app.exception;

import com.employee.app.error.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method is handling EmployeeNotFoundException
     *
     * @param exception
     * @param webRequest
     * @return ResponseEntity<ErrorDetails>
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(
            EmployeeNotFoundException exception,
            WebRequest webRequest) {

        log.error("EmployeeNotFoundException Occurred!");

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * This method is handling EmptyReportException
     *
     * @param exception
     * @param webRequest
     * @return ResponseEntity<ErrorDetails>
     */
    @ExceptionHandler(EmptyReportException.class)
    public ResponseEntity<ErrorDetails> handleEmptyReportException(
            EmptyReportException exception,
            WebRequest webRequest) {

        log.error("EmptyReportException Occurred!");

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
