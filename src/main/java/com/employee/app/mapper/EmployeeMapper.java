package com.employee.app.mapper;

import com.employee.app.dto.EmployeeDTO;
import com.employee.app.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    Employee mapToEntity(EmployeeDTO employeeDTO);

    List<Employee> mapToEntity(List<EmployeeDTO> employeeDTOs);

    EmployeeDTO mapToDTO(Employee employee);

    List<EmployeeDTO> mapToDTO(List<Employee> employees);
}
