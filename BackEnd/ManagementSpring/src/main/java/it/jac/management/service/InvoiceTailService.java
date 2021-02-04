package it.jac.management.service;

import java.util.Optional;

import it.jac.management.model.InvoiceTail;

public interface InvoiceTailService {

	public Optional<InvoiceTail> get(Long id);

	public InvoiceTail create(InvoiceTail i);

	public void delete(Long id);

	public InvoiceTail update(InvoiceTail i, Long id);

}