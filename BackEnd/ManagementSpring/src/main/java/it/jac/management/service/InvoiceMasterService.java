package it.jac.management.service;

import java.util.Optional;

import it.jac.management.model.InvoiceMaster;

public interface InvoiceMasterService {

	public Optional<InvoiceMaster> get(Long id);

	public InvoiceMaster create(InvoiceMaster i);
	
	public void delete(Long id);
	
	public InvoiceMaster update(InvoiceMaster i, Long id);

}