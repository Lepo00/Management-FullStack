package it.jac.management.service.impl;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import it.jac.management.service.XlsxService;

@Service
public class XlsxServiceImpl implements XlsxService {

	@Override
	public void createCell(int n, Row row, CellStyle style, String value) {
		Cell cell = row.createCell(n);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}

	@Override
	public void createCell(int n, Row row, CellStyle style, Date value) {
		Cell cell = row.createCell(n);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}

	@Override
	public void createCell(int n, Row row, CellStyle style, Long value) {
		Cell cell = row.createCell(n);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}

	@Override
	public void createCell(int n, Row row, CellStyle style, int value) {
		Cell cell = row.createCell(n);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}

	@Override
	public void createCell(int n, Row row, CellStyle style, Double value) {
		Cell cell = row.createCell(n);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}

	@Override
	public Font createFont(Workbook workbook, boolean bold, short size, short color) {
		Font font = workbook.createFont();
		font.setBold(bold);
		font.setFontHeightInPoints(size);
		font.setColor(color);
		return font;
	}

	@Override
	public CellStyle createCellStyle(Workbook workbook, Font font, HorizontalAlignment alignment) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(alignment);
		return cellStyle;
	}

	@Override
	public CellStyle createCellStyle(Workbook workbook, Font font, HorizontalAlignment alignment, short format) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(alignment);
		cellStyle.setDataFormat(format);
		return cellStyle;
	}

}
