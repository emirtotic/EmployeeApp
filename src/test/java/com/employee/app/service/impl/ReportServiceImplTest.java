package com.employee.app.service.impl;

import com.employee.app.dto.EmployeeDTO;
import com.employee.app.entity.Employee;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.service.EmployeeTrackerService;
import com.employee.app.service.JasperService;
import com.employee.app.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ReportServiceImplTest {

    private EmployeeTrackerService employeeTrackerService;
    private JasperService jasperService;
    private ReportService reportService;
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        employeeTrackerService = mock(EmployeeTrackerService.class);
        jasperService = mock(JasperService.class);
        employeeMapper = mock(EmployeeMapper.class);
        reportService = new ReportServiceImpl(employeeTrackerService, jasperService, employeeMapper);
    }

    @Test
    @DisplayName("Create PDF report Test")
    void createPdfReportTest() {

        when(jasperService.exportReportToPDF(anyString(), anyMap(), anyCollection()))
                .thenReturn(new ByteArrayInputStream(new byte[0]));
        when(employeeTrackerService.findByFilter(anyLong(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(new EmployeeDTO()));
        when(employeeMapper.mapToEntity(anyList())).thenReturn(Collections.singletonList(new Employee()));

        reportService.createPdfReport("Development");
        verify(jasperService, times(1)).exportReportToPDF(anyString(), anyMap(), anyCollection());
    }

    @Test
    @DisplayName("Create Excel report Test")
    void createExcelReportTest() {

        when(jasperService.exportReportExcel(anyString(), anyMap(), anyCollection()))
                .thenReturn(new ByteArrayInputStream(new byte[0]));
        when(employeeTrackerService.findByFilter(anyLong(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(new EmployeeDTO()));
        when(employeeMapper.mapToEntity(anyList())).thenReturn(Collections.singletonList(new Employee()));

        reportService.createExcelReport("Development");
        verify(jasperService, times(1)).exportReportExcel(anyString(), anyMap(), anyCollection());
    }
}