package com.inventory.service;

import com.inventory.entity.Report;
import com.inventory.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportRepository reportRepository;

	@PersistenceContext
	private EntityManager entityManager;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Report generateReport(String type, LocalDate start, LocalDate end) {
		String resultData;

		switch (type.toLowerCase()) {
		case "inventory":
			resultData = fetchInventoryData();
			break;
		case "order":
			resultData = fetchOrderData(start, end);
			break;
		case "supplier":
			resultData = fetchSupplierData();
			break;
		default:
			throw new IllegalArgumentException("Invalid report type.");
		}

		Report report = new Report(null, type, start, end, resultData);
		return reportRepository.save(report);
	}

	private String fetchInventoryData() {
		List<Object[]> rows = entityManager.createQuery(
				"SELECT p.name, s.quantity FROM Product p JOIN Stock s ON p.productId = s.product.productId",
				Object[].class).getResultList();

		return convertRowsToJson(rows, Arrays.asList("productName", "quantity"));
	}

	private String fetchOrderData(LocalDate start, LocalDate end) {
		List<Object[]> rows = entityManager.createQuery(
				"SELECT o.orderId, o.orderDate, o.status FROM Order o WHERE o.orderDate BETWEEN :start AND :end",
				Object[].class).setParameter("start", start).setParameter("end", end).getResultList();

		return convertRowsToJson(rows, Arrays.asList("orderId", "orderDate", "status"));
	}

	private String fetchSupplierData() {
		List<Object[]> rows = entityManager.createQuery("SELECT s.name, s.contactInfo FROM Supplier s", Object[].class)
				.getResultList();

		return convertRowsToJson(rows, Arrays.asList("supplierName", "contactInfo"));
	}

	private String convertRowsToJson(List<Object[]> rows, List<String> columnNames) {
		try {
			List<Map<String, Object>> formattedList = new ArrayList<>();
			for (Object[] row : rows) {
				Map<String, Object> rowMap = new HashMap<>();
				for (int i = 0; i < row.length; i++) {
					rowMap.put(columnNames.get(i), row[i]);
				}
				formattedList.add(rowMap);
			}
			return objectMapper.writeValueAsString(formattedList);
		} catch (Exception e) {
			throw new RuntimeException("Error converting report data to JSON", e);
		}
	}

	@Override
	public List<Report> getAllReports() {
		return reportRepository.findAll();
	}

	@Override
	public Report getReportById(Long id) {
		return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found with ID: " + id));
	}
}