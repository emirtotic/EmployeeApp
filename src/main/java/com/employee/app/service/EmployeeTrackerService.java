package com.employee.app.service;

import com.employee.app.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeTrackerService {

    List<EmployeeDTO> findAllEmployees();
    List<EmployeeDTO> findByFiler(Long personalId, String name, String team, String teamLead);
    EmployeeDTO findById(long id);
    void deleteEmployee(long id);
    EmployeeDTO create(EmployeeDTO employeeDTO);
    EmployeeDTO update(EmployeeDTO employeeDTO);

}
