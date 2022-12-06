package com.employee.app.service.impl;

import com.employee.app.entity.Employee;
import com.employee.app.entity.EmployeeReport;
import com.employee.app.exception.EmptyReportException;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.service.EmployeeTrackerService;
import com.employee.app.service.JasperService;
import com.employee.app.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final EmployeeTrackerService employeeTrackerService;
    private final JasperService jasperService;
    private final EmployeeMapper employeeMapper;

    /**
     * Create PDF employees report based on team
     *
     * @param team
     * @return InputStream for creating pdf report
     */
    @Override
    public InputStream createPdfReport(String team) {

        log.info("Generating Employees PDF report...");

        Map<String, Object> params = new HashMap<>();
        EmployeeReport employeeReport = prepareDataForEmployeeReport(team);
        params.put("employeeReport", employeeReport);

        return jasperService.exportReportToPDF("/report/employee-report.jrxml", params, employeeReport.getEmployees());
    }

    /**
     * Create Excel employees report
     *
     * @param team
     * @return InputStream for creating excel .xlsx report
     */
    @Override
    public InputStream createExcelReport(String team) {

        log.info("Generating Employees Excel report...");

        Map<String, Object> params = new HashMap<>();
        EmployeeReport employeeReport = prepareDataForEmployeeReport(team);
        params.put("employeeReport", employeeReport);

        return jasperService.exportReportExcel("/report/employee-report.jrxml", params, employeeReport.getEmployees());
    }

    private EmployeeReport prepareDataForEmployeeReport(String team) {

        EmployeeReport employeeReport = new EmployeeReport();
        List<Employee> employees;

        if (team != null) {
            employeeReport.setTitle(team + " Employees");
            employeeReport.setDescription("You can find a list of Employees from " + team + " team below");
            employees = employeeMapper.mapToEntity(
                    employeeTrackerService.findByFilter(null, null, team, null));
        } else {
            employeeReport.setTitle("Our Employees");
            employeeReport.setDescription("You can find a list of all employees from our company below");
            employees = employeeMapper.mapToEntity(employeeTrackerService.findAllEmployees());
        }

        if (employees.isEmpty()) {
            throw new EmptyReportException();
        }

        employeeReport.setEmployees(employees);
        employeeReport.setTotalEmployees(employees.size());

        return employeeReport;
    }
}
