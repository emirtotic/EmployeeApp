package com.employee.app.service;

import com.employee.app.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeTrackerService {

    /**
     * Finding all employees from database
     *
     * @return List<EmployeeDTO>
     */
    List<EmployeeDTO> findAllEmployees();

    /**
     * Finding all employees from database based on passed filters
     *
     * @param personalId
     * @param name
     * @param team
     * @param teamLead
     * @return List<EmployeeDTO>
     */
    List<EmployeeDTO> findByFilter(Long personalId, String name, String team, String teamLead);

    /**
     * Finding an employee based on passed id
     *
     * @param id
     * @return EmployeeDTO
     */
    EmployeeDTO findById(long id);

    /**
     * Delete an employee based on passed id
     *
     * @param id
     */
    void deleteEmployee(long id);

    /**
     * Creating new employee
     *
     * @param employeeDTO
     * @return EmployeeDTO
     */
    EmployeeDTO create(EmployeeDTO employeeDTO);

    /**
     * Updating an existing employee
     *
     * @param id
     * @param employeeDTO
     * @return EmployeeDTO
     */
    EmployeeDTO update(Long id, EmployeeDTO employeeDTO);
}
