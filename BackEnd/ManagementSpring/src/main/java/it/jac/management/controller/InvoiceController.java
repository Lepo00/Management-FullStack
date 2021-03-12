package it.jac.management.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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

import it.jac.management.model.InvoiceBody;
import it.jac.management.model.InvoiceMaster;
import it.jac.management.model.ResponseMessage;
import it.jac.management.service.InvoiceMasterService;
import it.jac.management.service.XlsxService;

@RestController
@RequestMapping("/invoice")
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {

	@Autowired
	InvoiceMasterService invoiceService;
	@Autowired
	XlsxService xlsxService;

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

	@GetMapping(path = "/{id}/xlsx")
	public void exportCsv(@PathVariable Long id, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Fattura-" + id + ".xlsx");
		InvoiceMaster invoice = invoiceService.get(id).get();
		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		/*
		 * CreationHelper helps us create instances of various things like DataFormat,
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		 */
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Invoice");

		// Create a Font for styling header cells
		Font titleFont = xlsxService.createFont(workbook, true, (short) 23, IndexedColors.RED.getIndex());
		Font headerFont = xlsxService.createFont(workbook, false, (short) 15, IndexedColors.RED.getIndex());
		Font textFont = xlsxService.createFont(workbook, false, (short) 12, IndexedColors.BLACK.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = xlsxService.createCellStyle(workbook, headerFont, HorizontalAlignment.CENTER);
		CellStyle titleCellStyle = xlsxService.createCellStyle(workbook, titleFont, HorizontalAlignment.CENTER);
		CellStyle textCellStyle = xlsxService.createCellStyle(workbook, textFont, HorizontalAlignment.CENTER);
		CellStyle currencyCellStyle = xlsxService.createCellStyle(workbook, textFont, HorizontalAlignment.CENTER, (short)8);

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = xlsxService.createCellStyle(workbook, textFont, HorizontalAlignment.CENTER);
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		// merge title cells
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));

		// Create a Row into createCell
		xlsxService.createCell(0, sheet.createRow(0), titleCellStyle, "Fattura-" + id);

		Row headerRow = sheet.createRow(1);
		String[] headMaster = { "Numero", "Intestatario", "Data", "Pagamento", "Totale" };
		for (int i = 0; i < headMaster.length; i++) {
			xlsxService.createCell(i + 2, headerRow, headerCellStyle, headMaster[i]);
		}

		Row master = sheet.createRow(2);

		xlsxService.createCell(2, master, textCellStyle, invoice.getId());
		xlsxService.createCell(3, master, textCellStyle, invoice.getAccountholder());
		xlsxService.createCell(4, master, dateCellStyle, invoice.getDate());
		xlsxService.createCell(5, master, textCellStyle, invoice.getPaymentMethod());
		xlsxService.createCell(6, master, currencyCellStyle, invoice.getTail().getFinalAmount());

		xlsxService.createCell(0, sheet.createRow(4), titleCellStyle, "Righe");

		headerRow = sheet.createRow(5);
		String[] headBody = { "Oggetto", "Quantità", "Prezzo un.", "Sconto %", "Val. sconto", "Netto", "Imponibile",
				"IVA", "Totale" };
		for (int i = 0; i < headBody.length; i++) {
			xlsxService.createCell(i, headerRow, headerCellStyle, headBody[i]);
		}

		int contRows = 6;
		for (InvoiceBody row : invoice.getRows()) {
			Row body = sheet.createRow(contRows++);
			xlsxService.createCell(0, body, textCellStyle, row.getItem().getDescription());
			xlsxService.createCell(1, body, textCellStyle, row.getQuantity());
			xlsxService.createCell(2, body, currencyCellStyle, row.getItem().getPrice());
			xlsxService.createCell(3, body, textCellStyle, row.getPercDiscount());
			xlsxService.createCell(4, body, currencyCellStyle, row.getTotDiscount());
			xlsxService.createCell(5, body, currencyCellStyle, row.getNetPrice());
			xlsxService.createCell(6, body, currencyCellStyle, row.getTaxable());
			xlsxService.createCell(7, body, currencyCellStyle, row.getTaxed());
			xlsxService.createCell(8, body, currencyCellStyle, row.getFinalAmount());
		}

		xlsxService.createCell(0, sheet.createRow(++contRows), titleCellStyle, "Coda");
		sheet.addMergedRegion(new CellRangeAddress(contRows, contRows, 0, 8));
		headerRow = sheet.createRow(++contRows);
		String[] headTail = { "ItemsValue?","Servizi", "Sconto righe", "Sconto % coda", "Val. sconto coda",
				"Totale sconto", "Imponibile", "IVA", "Totale" };
		for (int i = 0; i < headTail.length; i++) {
			xlsxService.createCell(i, headerRow, headerCellStyle, headTail[i]);
		}

		Row tail = sheet.createRow(++contRows);

		xlsxService.createCell(0, tail, currencyCellStyle, invoice.getTail().getItemsValue());
		xlsxService.createCell(1, tail, currencyCellStyle, invoice.getTail().getServiceValue());
		xlsxService.createCell(2, tail, currencyCellStyle, invoice.getTail().getRowsDiscount());
		xlsxService.createCell(3, tail, textCellStyle, invoice.getTail().getPercDiscount());
		xlsxService.createCell(4, tail, currencyCellStyle, invoice.getTail().getDiscountValue());
		xlsxService.createCell(5, tail, currencyCellStyle, invoice.getTail().getTotDiscount());
		xlsxService.createCell(6, tail, currencyCellStyle, invoice.getTail().getTaxable());
		xlsxService.createCell(7, tail, currencyCellStyle, invoice.getTail().getTaxed());
		xlsxService.createCell(8, tail, currencyCellStyle, invoice.getTail().getFinalAmount());

		// Resize all columns to fit the content size
		for (int i = 0; i < headBody.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		workbook.write(response.getOutputStream());

		// Closing the workbook
		workbook.close();
	}

}
