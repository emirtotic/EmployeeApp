package com.employee.app.service.impl;

import com.employee.app.dto.EmployeeDTO;
import com.employee.app.entity.Employee;
import com.employee.app.exception.EmployeeNotFoundException;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.repository.EmployeeTrackerRepository;
import com.employee.app.service.EmployeeTrackerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EmployeeTrackerServiceImplTest {

    private EmployeeTrackerService employeeTrackerService;
    private EmployeeTrackerRepository employeeTrackerRepository;
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {

        employeeTrackerRepository = mock(EmployeeTrackerRepository.class);
        employeeMapper = mock(EmployeeMapper.class);
        employeeTrackerService = new EmployeeTrackerServiceImpl(employeeTrackerRepository, employeeMapper);
    }

    @Test
    @DisplayName("Find Employee By Id Test")
    void findByIdTest() {

        Employee employee = createEmployee();

        when(employeeTrackerRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeMapper.mapToDTO(any(Employee.class))).thenReturn(new EmployeeDTO());

        employeeTrackerService.findById(anyLong());

        verify(employeeTrackerRepository, times(1)).findById(anyLong());
        verify(employeeMapper, times(1)).mapToDTO(any(Employee.class));
    }

    @Test
    @DisplayName("Find Employee By Id Throws Exception Test")
    void findByIdThrowsExceptionTest() {

        when(employeeTrackerRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeTrackerService.findById(100100L));

        verify(employeeTrackerRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Find All Employees Test")
    void findAllEmployeesTest() {

        when(employeeTrackerRepository.findAll()).thenReturn(Collections.singletonList(createEmployee()));
        when(employeeMapper.mapToDTO(anyList())).thenReturn(Collections.singletonList(createEmployeeDTO()));

        employeeTrackerService.findAllEmployees();

        verify(employeeTrackerRepository, times(1)).findAll();
        verify(employeeMapper, times(1)).mapToDTO(anyList());
    }

    @Test
    @DisplayName("Find All Employees by filter Test")
    void findByFilerTest() {

        when(employeeTrackerRepository.findByFilter(anyLong(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(createEmployee()));
        when(employeeMapper.mapToDTO(anyList())).thenReturn(Collections.singletonList(createEmployeeDTO()));

        employeeTrackerService.findByFiler(null, null, "Sales", null);

        verify(employeeTrackerRepository, times(1))
                .findByFilter(null, null, "Sales", null);
        verify(employeeMapper, times(1)).mapToDTO(anyList());
    }

    @Test
    @DisplayName("Delete Employee Test")
    void deleteEmployeeTest() {

        when(employeeTrackerRepository.findById(anyLong())).thenReturn(Optional.of(new Employee()));
        doNothing().when(employeeTrackerRepository).deleteById(anyLong());
        employeeTrackerService.deleteEmployee(100100L);
        verify(employeeTrackerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Create Employee Test")
    void createTestTest() {

        EmployeeDTO employeeDTO = createEmployeeDTO();
        Employee employee = createEmployee();

        when(employeeMapper.mapToEntity(any(EmployeeDTO.class))).thenReturn(employee);
        when(employeeMapper.mapToDTO(any(Employee.class))).thenReturn(new EmployeeDTO());
        when(employeeTrackerRepository.save(any())).thenReturn(employee);

        employeeTrackerService.create(employeeDTO);

        verify(employeeMapper, times(1)).mapToDTO(any(Employee.class));
        verify(employeeMapper, times(1)).mapToEntity(any(EmployeeDTO.class));
        verify(employeeTrackerRepository, times(1)).save(any(Employee.class));
    }

    @Test
    @DisplayName("Update Employee Test")
    void updateTest() {

        Employee employee = createEmployee();
        EmployeeDTO employeeDTO = createEmployeeDTO();

        when(employeeTrackerRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeMapper.mapToEntity(any(EmployeeDTO.class))).thenReturn(employee);
        when(employeeMapper.mapToDTO(any(Employee.class))).thenReturn(employeeDTO);
        when(employeeTrackerRepository.save(any())).thenReturn(employee);

        employeeTrackerService.update(employeeDTO);

        verify(employeeTrackerRepository, times(1)).save(any());
        verify(employeeMapper, times(1)).mapToEntity(any(EmployeeDTO.class));
        verify(employeeMapper, times(1)).mapToDTO(any(Employee.class));
    }

    private Employee createEmployee() {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setPersonalId(192837L);
        employee.setName("Stefan");
        employee.setTeam("Development");
        employee.setTeamLead("Mirko");

        return employee;
    }

    private EmployeeDTO createEmployeeDTO() {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(3L);
        employeeDTO.setPersonalId(738291L);
        employeeDTO.setName("Filip");
        employeeDTO.setTeam("Development");
        employeeDTO.setTeamLead("Nemanja");

        return employeeDTO;
    }
}