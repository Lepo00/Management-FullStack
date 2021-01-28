package it.jac.management.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.Invoice;
import it.jac.management.repository.InvoiceRepository;
import it.jac.management.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public Optional<Invoice> get(Long id){
		return invoiceRepository.findById(id);
	}

	@Override
	public Invoice create(Invoice i){
		return invoiceRepository.save(i);
	}

	@Override
	public void delete(Long id){
		invoiceRepository.deleteById(id);
	}

	@Override
	public Invoice update(Invoice invoice, Long id){
		return invoiceRepository.findById(id).map(c -> { // update if entity already exists
			invoice.setId(c.getId());
			return create(invoice);
		}).orElseGet(() -> { // create if entity not exists
			return create(invoice);
		});
	}


}
