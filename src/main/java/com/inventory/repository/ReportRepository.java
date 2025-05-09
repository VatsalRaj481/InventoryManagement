package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{

}
