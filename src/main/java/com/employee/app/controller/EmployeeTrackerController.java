package com.employee.app.controller;

import com.employee.app.dto.EmployeeDTO;
import com.employee.app.service.EmployeeTrackerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/employee-tracker", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee Tracker", description = "List of Employee Tracker Controller methods")
@Validated
public class EmployeeTrackerController {

    private final EmployeeTrackerService employeeTrackerService;

    @Operation(summary = "Find all Employees", description = "Retrieving all employees from DB")
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return new ResponseEntity<>(employeeTrackerService.findAllEmployees(), HttpStatus.OK);
    }

    @Operation(summary = "Find Employees by filter", description = "Retrieving all employees by filter search")
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> findByFilter(@RequestParam(required = false) Long personalId,
                                                          @RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String team,
                                                          @RequestParam(required = false) String teamLead) {

        return new ResponseEntity<>(employeeTrackerService.findByFiler(personalId, name, team, teamLead), HttpStatus.OK);
    }

    @Operation(summary = "Create Employee", description = "Creating New Employee and saving into DB")
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> create(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeTrackerService.create(employeeDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Find Employee by ID", description = "Retrieving the employee by id parameter from DB")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(employeeTrackerService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update Employee", description = "Updating Employee and saving into DB")
    @PutMapping("/update")
    public ResponseEntity<EmployeeDTO> update(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeTrackerService.update(employeeDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete Employee by ID", description = "Deleting the employee from Database by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        employeeTrackerService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
