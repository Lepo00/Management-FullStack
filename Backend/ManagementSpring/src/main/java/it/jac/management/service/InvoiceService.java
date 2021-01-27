package it.jac.management.service;

import java.util.Optional;

import it.jac.management.model.Invoice;

public interface InvoiceService {

	public Optional<Invoice> get(Long id);

	public Invoice create(Invoice i);
	
	public void delete(Long id);
	
	public Invoice update(Invoice i, Long id);

}