package it.jac.management.controller;

import java.util.Optional;

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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice doesn't exists");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> newInvoice(@RequestBody InvoiceMaster invoice) throws Exception {
		try {

			InvoiceMaster save = invoiceService.create(invoice);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok("Invoice saved with id:" + invoice.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invoice Not Saved!");
		}
	}

	@PostMapping("/save/{idCustomer}")
	public ResponseEntity<?> newInvoice(@PathVariable Long idCustomer, @RequestBody InvoiceMaster invoice)
			throws Exception {
		try {
			invoiceService.createWithCustomer(idCustomer, invoice);
			return ResponseEntity.ok("Invoice saved with id:" + invoice.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invoice Not Saved!");
		}
	}

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> updateInvoice(@PathVariable Long id, @RequestBody InvoiceMaster invoice) {
		try {
			InvoiceMaster update = invoiceService.update(invoice, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invoice Not Updated!");
		}
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
		try {
			invoiceService.delete(id);
			return ResponseEntity.ok().body("Invoice deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice doesn't exists");
		}
	}

}
