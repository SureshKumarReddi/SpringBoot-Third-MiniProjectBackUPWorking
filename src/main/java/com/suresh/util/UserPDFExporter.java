package com.suresh.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.suresh.bindings.PlanResponse;
import com.suresh.entity.InsurancePlans;

public class UserPDFExporter {
	private List<PlanResponse> plans;

	public UserPDFExporter(List<PlanResponse> listPlans) {
		this.plans = listPlans;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Plan ID", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Pllan Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Holder Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Holder SSN", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (PlanResponse plan : plans) {
			table.addCell(String.valueOf(plan.getPlanId()));
			table.addCell(plan.getPlanName());
			table.addCell(plan.getHolderName());
			table.addCell(String.valueOf(plan.getHolderSsn()));
			table.addCell(plan.getPlanStatus());
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Insurance Plans", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}