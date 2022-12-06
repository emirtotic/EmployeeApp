package com.employee.app.service;

import java.io.InputStream;

public interface ReportService {

    /**
     * Create PDF employees report based on team
     *
     * @param team
     * @return InputStream for creating pdf report
     */
    InputStream createPdfReport(String team);

    /**
     * Create Excel report
     *
     * @param team
     * @return InputStream for creating excel .xlsx report
     */
    InputStream createExcelReport(String team);
}
