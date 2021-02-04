package it.jac.management.service;

import java.util.Optional;

import it.jac.management.model.InvoiceBody;

public interface InvoiceBodyService {

	public Optional<InvoiceBody> get(Long id);

	public InvoiceBody create(InvoiceBody i);

	public void delete(Long id);

	public InvoiceBody update(InvoiceBody i, Long id);

}