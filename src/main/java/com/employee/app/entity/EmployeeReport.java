package com.employee.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeReport {

    private String title;
    private String description;
    private int totalEmployees;
    private List<Employee> employees;
}
