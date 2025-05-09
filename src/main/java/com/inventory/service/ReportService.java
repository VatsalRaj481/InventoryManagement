package com.inventory.service;

import java.time.LocalDate;
import java.util.List;

import com.inventory.entity.Report;

public interface ReportService {
    Report generateReport(String type, LocalDate start, LocalDate end);
    List<Report> getAllReports();
    Report getReportById(Long id);
}

