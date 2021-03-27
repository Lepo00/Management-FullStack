package it.jac.management.service;

import java.util.List;
import java.util.Optional;

import it.jac.management.model.InvoiceBody;

public interface InvoiceBodyService {

	public Optional<InvoiceBody> get(Long id);

	public InvoiceBody create(InvoiceBody i);
	
	public List<InvoiceBody> createAll(List<InvoiceBody> invoices); 

	public void delete(Long id);

	public InvoiceBody update(InvoiceBody i, Long id);
	
	public List<InvoiceBody> calc(List<InvoiceBody> rows);

}