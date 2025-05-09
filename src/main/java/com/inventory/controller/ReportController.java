package com.inventory.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.ReportRequestDto;
import com.inventory.entity.Report;
import com.inventory.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    @Autowired 
    private ReportService reportService;

    @PostMapping("/generate")
    public ResponseEntity<Report> generateReport(@RequestBody ReportRequestDto request) {
    	Report report = reportService.generateReport(
    			request.getReporttype(), request.getStartDate(), request.getEndDate());
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public List<Report> getAll() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public Report getById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }
    
    
}
