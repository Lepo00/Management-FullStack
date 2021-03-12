package it.jac.management.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
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
	XlsxService xlsx;

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
	public ResponseEntity<String> exportCsv(@PathVariable Long id, HttpServletResponse response) {
		InvoiceMaster invoice = invoiceService.get(id).get();
		Workbook doc = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xlsx` file
		Sheet sheet = doc.createSheet("Invoice");

		// Create fonts
		Font h1Font = xlsx.createFont(doc, true, (short) 23, IndexedColors.RED.getIndex());
		Font h2Font = xlsx.createFont(doc, true, (short) 16, IndexedColors.RED.getIndex());
		Font h3Font = xlsx.createFont(doc, false, (short) 14, IndexedColors.RED.getIndex());
		Font textFont = xlsx.createFont(doc, false, (short) 13, IndexedColors.BLACK.getIndex());

		// Create CellStyles with fonts
		CellStyle h1Style = xlsx.createCellStyle(doc, h1Font, HorizontalAlignment.CENTER);
		CellStyle h2Style = xlsx.createCellStyle(doc, h2Font, HorizontalAlignment.CENTER);
		CellStyle h3Style = xlsx.createCellStyle(doc, h3Font, HorizontalAlignment.CENTER);
		CellStyle textStyle = xlsx.createCellStyle(doc, textFont, HorizontalAlignment.CENTER);
		CellStyle currencyStyle = xlsx.createCellStyle(doc, textFont, HorizontalAlignment.CENTER, (short) 8);
		CellStyle percentageStyle = xlsx.createCellStyle(doc, textFont, HorizontalAlignment.CENTER, (short) 10);
		CellStyle dateStyle = xlsx.createDateCellStyle(doc, textFont, HorizontalAlignment.CENTER, "dd-MM-yyyy");

		//Initial row & initial column
		int initRow = 1;
		int initColumn = 1;
		int contRows = initRow;

		// Create a Row into createCell
		xlsx.createCell(initColumn, sheet.createRow(contRows++), h1Style, "Fattura-" + id);
		xlsx.createCell(initColumn, sheet.createRow(contRows++), h2Style, "Testa");

		for (int i = 1; i < 9; i += 2) {
			CellRangeAddress accountHolderRegion = new CellRangeAddress(contRows, contRows, initColumn + i, initColumn + i + 1);
			CellRangeAddress accountHolderValueRegion = new CellRangeAddress(contRows + 1, contRows + 1, initColumn + i, initColumn + i + 1);
			xlsx.mergeRegions(new CellRangeAddress[] {accountHolderRegion, accountHolderValueRegion}, sheet);
		}
		
		Row headerRow = sheet.createRow(contRows++);
		String[] headMaster = { "Numero", "Intestatario", "Data", "Pagamento", "Totale Fattura" };
		xlsx.createCell(initColumn, headerRow, h3Style, headMaster[0]);
		for (int i = 1, cont = 1; i < headMaster.length; i++, cont += 2) {
			xlsx.createCell(cont + initColumn, headerRow, h3Style, headMaster[i]);
		}

		Row master = sheet.createRow(contRows++);
		xlsx.createCell(initColumn, master, textStyle, invoice.getId());
		xlsx.createCell(initColumn + 1, master, textStyle, invoice.getAccountholder());
		xlsx.createCell(initColumn + 3, master, dateStyle, invoice.getDate());
		xlsx.createCell(initColumn + 5, master, textStyle, invoice.getPaymentMethod());
		xlsx.createCell(initColumn + 7, master, currencyStyle, invoice.getTail().getFinalAmount());

		xlsx.createCell(initColumn, sheet.createRow(contRows++), h2Style, "Righe");

		headerRow = sheet.createRow(contRows++);
		String[] headBody = { "Oggetto", "Quantità", "Prezzo Unitario", "Sconto %", "Valore sconto", "Netto",
				"Imponibile", "IVA", "Totale Riga" };
		for (int i = 0; i < headBody.length; i++) {
			xlsx.createCell(i + initColumn, headerRow, h3Style, headBody[i]);
		}

		Row body = null;
		for (InvoiceBody row : invoice.getRows()) {
			body = sheet.createRow(contRows++);
			xlsx.createCell(initColumn, body, textStyle, row.getItem().getDescription());
			xlsx.createCell(initColumn + 1, body, textStyle, row.getQuantity());
			xlsx.createCell(initColumn + 2, body, currencyStyle, row.getItem().getPrice());
			xlsx.createCell(initColumn + 3, body, percentageStyle, row.getPercDiscount());
			xlsx.createCell(initColumn + 4, body, currencyStyle, row.getTotDiscount());
			xlsx.createCell(initColumn + 5, body, currencyStyle, row.getNetPrice());
			xlsx.createCell(initColumn + 6, body, currencyStyle, row.getTaxable());
			xlsx.createCell(initColumn + 7, body, currencyStyle, row.getTaxed());
			xlsx.createCell(initColumn + 8, body, currencyStyle, row.getFinalAmount());
		}
		
		xlsx.createCell(initColumn, sheet.createRow(contRows++), h2Style, "Coda");
		headerRow = sheet.createRow(contRows++);
		String[] headTail = { "Imponibile Righe", "Servizi", "Sconto Righe", "Sconto Coda %", "Valore Sconto Coda",
				"Totale Sconto", "Imponibile", "IVA", "Totale Fattura" };
		for (int i = 0; i < headTail.length; i++) {
			xlsx.createCell(i + initColumn, headerRow, h3Style, headTail[i]);
		}

		Row tail = sheet.createRow(contRows);
		xlsx.createCell(initColumn, tail, currencyStyle, invoice.getTail().getItemsValue());
		xlsx.createCell(initColumn + 1, tail, currencyStyle, invoice.getTail().getServiceValue());
		xlsx.createCell(initColumn + 2, tail, currencyStyle, invoice.getTail().getRowsDiscount());
		xlsx.createCell(initColumn + 3, tail, percentageStyle, invoice.getTail().getPercDiscount() / 100);
		xlsx.createCell(initColumn + 4, tail, currencyStyle, invoice.getTail().getDiscountValue());
		xlsx.createCell(initColumn + 5, tail, currencyStyle, invoice.getTail().getTotDiscount());
		xlsx.createCell(initColumn + 6, tail, currencyStyle, invoice.getTail().getTaxable());
		xlsx.createCell(initColumn + 7, tail, currencyStyle, invoice.getTail().getTaxed());
		xlsx.createCell(initColumn + 8, tail, currencyStyle, invoice.getTail().getFinalAmount());
		
		// create cells range
		CellRangeAddress titleRegion = new CellRangeAddress(initRow, initRow, initColumn, initColumn + 8);
		CellRangeAddress masterRegion = new CellRangeAddress(initRow + 1, initRow + 1, initColumn, initColumn + 8);
		CellRangeAddress bodyRegion = new CellRangeAddress(initRow + 4, initRow + 4, initColumn, initColumn + 8);
		CellRangeAddress invoiceRegion = new CellRangeAddress(initRow + 1, contRows, initColumn, initColumn + 8);
		CellRangeAddress tailRegion = new CellRangeAddress(headerRow.getRowNum()-1, headerRow.getRowNum()-1, initColumn, initColumn + 8);
		
		//merge cells range
		xlsx.mergeRegions(new CellRangeAddress[]{titleRegion, masterRegion, bodyRegion, tailRegion}, sheet);

		for (int i = 0; i < 9; i++) {
			xlsx.setBorders(
					new CellRangeAddress[] {
							new CellRangeAddress(initRow+2, initRow+3, initColumn+i, initColumn+i),
							new CellRangeAddress(tail.getRowNum()-1, tail.getRowNum(), initColumn+i, initColumn+i),
							new CellRangeAddress(body.getRowNum()-invoice.getRows().size(), body.getRowNum(), initColumn+i, initColumn+i)
					},
					BorderStyle.MEDIUM, sheet);
		}
		xlsx.setBorders(new CellRangeAddress[] {invoiceRegion, masterRegion, bodyRegion, tailRegion}, BorderStyle.THICK, sheet);

		// Resize all columns to fit the content size
		for (int i = 0; i < headBody.length; i++) {
			sheet.autoSizeColumn(i + initColumn);
		}

		// Write the output to the file to download
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Fattura-" + id + ".xlsx");
			doc.write(response.getOutputStream());
			doc.close();
			return ResponseEntity.ok().body("File written");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The file couldn't be written!");
		}
	}

}
