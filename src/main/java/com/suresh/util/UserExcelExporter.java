package com.suresh.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.suresh.bindings.PlanResponse;
import com.suresh.entity.InsurancePlans;

public class UserExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<PlanResponse> listPlans;

	public UserExcelExporter(List<PlanResponse> listPlans2) {
		this.listPlans = listPlans2;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Insurance Plans");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Plan ID", style);
		createCell(row, 1, "Plan Name", style);
		createCell(row, 2, "Holder Name", style);
		createCell(row, 3, "Holder SSN", style);
		createCell(row, 4, "Plan Status", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (PlanResponse plan : listPlans) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, plan.getPlanId(), style);
			createCell(row, columnCount++, plan.getPlanName(), style);
			createCell(row, columnCount++, plan.getHolderName(), style);
			createCell(row, columnCount++, plan.getHolderSsn().toString(), style);
			createCell(row, columnCount++, plan.getPlanStatus(), style);

		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
