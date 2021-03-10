package it.jac.management.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.management.model.InvoiceMaster;
import it.jac.management.model.ResponseMessage;
import it.jac.management.service.InvoiceMasterService;

@RestController
@RequestMapping("/invoice")
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {

	@Autowired
	InvoiceMasterService invoiceService;

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<InvoiceMaster> i = invoiceService.get(id);
		if (i.isPresent()) {
			return ResponseEntity.ok(i.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Invoice doesn't exists"));
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> newInvoice(@RequestBody InvoiceMaster invoice) throws Exception {
		try {

			InvoiceMaster save = invoiceService.create(invoice);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Invoice Not Saved!"));
		}
	}

	@PostMapping("/save/{idCustomer}")
	public ResponseEntity<?> newInvoice(@PathVariable Long idCustomer, @RequestBody InvoiceMaster invoice)
			throws Exception {
		try {
			InvoiceMaster save = invoiceService.createWithCustomer(idCustomer, invoice);
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Invoice Not Saved!"));
		}
	}

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> updateInvoice(@PathVariable Long id, @RequestBody InvoiceMaster invoice) {
		try {
			InvoiceMaster update = invoiceService.update(invoice, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Invoice Not Updated!"));
		}
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<ResponseMessage> deleteInvoice(@PathVariable Long id) {
		try {
			invoiceService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("Invoice deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Invoice doesn't exists"));
		}
	}

	@GetMapping(path = "/{id}/csv")
	public void exportCsv(@PathVariable Long id, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Fattura" + id + ".xlsx");
		InvoiceMaster invoice = invoiceService.get(id).get();
		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		/*
		 * CreationHelper helps us create instances of various things like DataFormat,
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		 */
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Employee");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		String[] columns = { "Num.", "Intestatario", "Data", "Pagamento" };
		// Create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		// Create Other rows and cells with employees data
		int rowNum = 1;
		Row row = sheet.createRow(rowNum++);

		row.createCell(0).setCellValue(invoice.getId());

		row.createCell(1).setCellValue(invoice.getAccountholder());

		Cell date = row.createCell(2);
		date.setCellValue(invoice.getDate());
		date.setCellStyle(dateCellStyle);

		row.createCell(3).setCellValue(invoice.getPaymentMethod());

		Row rows = sheet.createRow(3);
		rows.createCell(0).setCellValue("prova");

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		workbook.write(response.getOutputStream());

		// Closing the workbook
		workbook.close();
	}

}
