package com.employee.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends ApplicationException {

    /**
     * Constructs a new EmployeeNotFoundException exception
     *
     * @param employeeId
     */
    public EmployeeNotFoundException(Long employeeId) {
        super(String.format("Employee with id=[%s] is not found", employeeId));
    }
}
