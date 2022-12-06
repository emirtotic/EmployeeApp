package com.employee.app.mapper;

import com.employee.app.dto.EmployeeDTO;
import com.employee.app.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    private EmployeeMapper employeeMapper;
    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp(){
        employeeMapper = Mappers.getMapper(EmployeeMapper.class);
        employee = createEmployee();
        employeeDTO = createEmployeeDTO();
    }

    @Test
    @DisplayName("EmployeeDTO to Employee Mapper Test")
    void mapToEntityTest() {

        Employee result = employeeMapper.mapToEntity(employeeDTO);

        assertEquals(employeeDTO.getPersonalId(), result.getPersonalId());
        assertEquals(employeeDTO.getName(), result.getName());
        assertEquals(employeeDTO.getTeam(), result.getTeam());
        assertEquals(employeeDTO.getTeamLead(), result.getTeamLead());
    }

    @Test
    @DisplayName("Employee to EmployeeDTO Mapper Test")
    void mapToDTOTest() {

        EmployeeDTO result = employeeMapper.mapToDTO(employee);

        assertEquals(employee.getPersonalId(), result.getPersonalId());
        assertEquals(employee.getName(), result.getName());
        assertEquals(employee.getTeam(), result.getTeam());
        assertEquals(employee.getTeamLead(), result.getTeamLead());
    }

    @Test
    @DisplayName("List<EmployeeDTO> to List<Employee> Mapper Test")
    void mapToEntityListTest() {

        List<Employee> results = employeeMapper.mapToEntity(Collections.singletonList(employeeDTO));

        assertEquals(employeeDTO.getPersonalId(), results.get(0).getPersonalId());
        assertEquals(employeeDTO.getName(), results.get(0).getName());
        assertEquals(employeeDTO.getTeam(), results.get(0).getTeam());
        assertEquals(employeeDTO.getTeamLead(), results.get(0).getTeamLead());
    }

    @Test
    @DisplayName("List<Employee> to List<EmployeeDTO> Mapper Test")
    void mapToDTOListTest() {

        List<EmployeeDTO> results = employeeMapper.mapToDTO(Collections.singletonList(employee));

        assertEquals(employee.getPersonalId(), results.get(0).getPersonalId());
        assertEquals(employee.getName(), results.get(0).getName());
        assertEquals(employee.getTeam(), results.get(0).getTeam());
        assertEquals(employee.getTeamLead(), results.get(0).getTeamLead());
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
        employeeDTO.setPersonalId(738291L);
        employeeDTO.setName("Filip");
        employeeDTO.setTeam("Development");
        employeeDTO.setTeamLead("Nemanja");

        return employeeDTO;
    }


}