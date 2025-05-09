package com.inventory.dto;

import java.time.LocalDate;

public class ReportRequestDto {

	private String reporttype;
	private LocalDate startDate;
	private LocalDate endDate;

	public ReportRequestDto() {

	}

	public ReportRequestDto(String reporttype, LocalDate startDate, LocalDate endDate) {
		super();
		this.reporttype = reporttype;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getReporttype() {
		return reporttype;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
