package com.employee.app.controller;

import com.employee.app.dto.EmployeeDTO;
import com.employee.app.service.EmployeeTrackerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EmployeeTrackerControllerTest {

    private EmployeeTrackerService employeeTrackerService;
    private EmployeeTrackerController employeeTrackerController;

    @BeforeEach
    void setUp() {
        employeeTrackerService = mock(EmployeeTrackerService.class);
        employeeTrackerController = new EmployeeTrackerController(employeeTrackerService);
    }

    @Test
    @DisplayName("Find all employees Test")
    void findAllEmployeesTest() {

        when(employeeTrackerService.findAllEmployees()).thenReturn(Collections.singletonList(new EmployeeDTO()));
        List<EmployeeDTO> list = employeeTrackerController.findAll().getBody();
        assertEquals(1, list.size());
        verify(employeeTrackerService, times(1)).findAllEmployees();
    }

    @Test
    @DisplayName("Find all employees by filter Test")
    void findByFilterTest() {

        when(employeeTrackerService.findByFilter(any(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(new EmployeeDTO()));
        List<EmployeeDTO> list = employeeTrackerController
                .findByFilter(112233L, "Sima", "Sales", "Marija").getBody();
        assertEquals(1, list.size());
        verify(employeeTrackerService, times(1)).findByFilter(any(), anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Create employee Test")
    void createTest() {

        EmployeeDTO employeeDTO = createEmployeeDTO();

        when(employeeTrackerService.create(any(EmployeeDTO.class))).thenReturn(employeeDTO);
        EmployeeDTO result = employeeTrackerController.create(employeeDTO).getBody();
        assertEquals(employeeDTO.getPersonalId(), result.getPersonalId());
        assertEquals(employeeDTO.getName(), result.getName());
        assertEquals(employeeDTO.getTeam(), result.getTeam());
        assertEquals(employeeDTO.getTeamLead(), result.getTeamLead());
        verify(employeeTrackerService, times(1)).create(any());
    }

    @Test
    @DisplayName("Find Employee by ID Test")
    void findByIdTest() {

        when(employeeTrackerService.findById(anyLong())).thenReturn(new EmployeeDTO());
        EmployeeDTO employeeDTO = employeeTrackerController.findById(100000L).getBody();
        assertNotNull(employeeDTO);
        verify(employeeTrackerService, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Update Employee Test")
    void updateTest() {

        EmployeeDTO employeeDTO = createEmployeeDTO();

        when(employeeTrackerService.update(anyLong(), any(EmployeeDTO.class))).thenReturn(employeeDTO);
        EmployeeDTO updatedEmployee = employeeTrackerController.update(3L, employeeDTO).getBody();
        assertEquals(employeeDTO.getPersonalId(), updatedEmployee.getPersonalId());
        assertEquals(employeeDTO.getName(), updatedEmployee.getName());
        assertEquals(employeeDTO.getTeam(), updatedEmployee.getTeam());
        assertEquals(employeeDTO.getTeamLead(), updatedEmployee.getTeamLead());
        verify(employeeTrackerService, times(1)).update(anyLong(), any());
    }

    @Test
    @DisplayName("Delete Employee by ID Test")
    void deleteByIdTest() {

        employeeTrackerController.deleteById(100000L);
        verify(employeeTrackerService, times(1)).deleteEmployee(anyLong());
    }

    private EmployeeDTO createEmployeeDTO() {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setPersonalId(738291L);
        employeeDTO.setName("Filip");
        employeeDTO.setTeam("Development");
        employeeDTO.setTeamLead("Nemanja");

        return employeeDTO;
    }
}