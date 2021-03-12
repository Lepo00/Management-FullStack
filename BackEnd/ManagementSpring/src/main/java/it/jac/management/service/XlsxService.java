package it.jac.management.service;

import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public interface XlsxService {

	public void createCell(int n, Row row, CellStyle style, String value);

	public void createCell(int n, Row row, CellStyle style, Date value);

	public void createCell(int n, Row row, CellStyle style, Long value);

	public void createCell(int n, Row row, CellStyle style, int value);

	public void createCell(int n, Row row, CellStyle style, Double value);

	public Font createFont(Workbook workbook, boolean bold, short size, short color);

	public CellStyle createCellStyle(Workbook workbook, Font font, HorizontalAlignment alignment);

	public CellStyle createCellStyle(Workbook workbook, Font font, HorizontalAlignment alignment, short format);
}