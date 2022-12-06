package com.employee.app.service.impl;

import com.employee.app.dto.EmployeeDTO;
import com.employee.app.entity.Employee;
import com.employee.app.exception.EmployeeNotFoundException;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.repository.EmployeeTrackerRepository;
import com.employee.app.service.EmployeeTrackerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeTrackerServiceImpl implements EmployeeTrackerService {

    private final EmployeeTrackerRepository employeeTrackerRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO findById(long id) {

        log.info("Finding an Employee with id {}.", id);
        Employee employee = employeeTrackerRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeMapper.mapToDTO(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAllEmployees() {

        log.info("Retrieving all Employees from database...");
        return employeeMapper.mapToDTO(employeeTrackerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findByFiler(Long personalId, String name, String team, String teamLead) {

        log.info("Searching for Employees from database...");
        return employeeMapper.mapToDTO(employeeTrackerRepository.findByFilter(personalId, name, team, teamLead));
    }

    @Override
    @Transactional
    public void deleteEmployee(long id) {

        log.warn("Attempting to delete Employee with id {}.", id);
        employeeTrackerRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeTrackerRepository.deleteById(id);
        log.info("Employee with id {} has been deleted successfully.", id);
    }

    @Override
    @Transactional
    public EmployeeDTO create(EmployeeDTO employeeDTO) {

        log.info("Creating new Employee {}.", employeeDTO);
        Employee employee = employeeMapper.mapToEntity(employeeDTO);
        return employeeMapper.mapToDTO(employeeTrackerRepository.save(employee));
    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO) {

        log.info("Attempting to update Employee with id {}.", employeeDTO.getId());
        Employee employee = employeeMapper.mapToEntity(employeeDTO);
        employee.setId(employeeDTO.getId());
        return employeeMapper.mapToDTO(employeeTrackerRepository.save(employee));
    }
}
