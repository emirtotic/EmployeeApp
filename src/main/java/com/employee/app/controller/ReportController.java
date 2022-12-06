package com.employee.app.controller;

import com.employee.app.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee-tracker/report")
@Tag(name = "Reports", description = "List of Report Controller methods")
@Validated
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "Create PDF Report of filtered employees", description = "Create team Employees PDF Report")
    @GetMapping("/pdf")
    public ResponseEntity<String> createPdfReport(@RequestParam(name = "team", required = false) String team,
                                                  final HttpServletResponse response) throws IOException {

        InputStream inputStream = reportService.createPdfReport(team);

        if (inputStream != null) {
            String generatedFileName = "EmployeeTracker.pdf";
            response.setContentType(URLConnection.guessContentTypeFromName(generatedFileName));
            response.setHeader("Content-Disposition", "attachment; filename=" + generatedFileName);

            try {
                final ServletOutputStream outputStream = response.getOutputStream();
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            } finally {
                IOUtils.closeQuietly(inputStream);
            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create XLSX Report", description = "Create Employees Excel Report")
    @GetMapping("/excel")
    public ResponseEntity<String> createExcelReport(@RequestParam(name = "team", required = false) String team,
                                                    final HttpServletResponse response) throws IOException {

        InputStream inputStream = reportService.createExcelReport(team);

        if (inputStream != null) {
            String generatedFileName = "EmployeeTracker.xlsx";
            response.setContentType(URLConnection.guessContentTypeFromName(generatedFileName));
            response.setHeader("Content-Disposition", "attachment; filename=" + generatedFileName);

            try {
                final ServletOutputStream outputStream = response.getOutputStream();
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            } finally {
                IOUtils.closeQuietly(inputStream);
            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
