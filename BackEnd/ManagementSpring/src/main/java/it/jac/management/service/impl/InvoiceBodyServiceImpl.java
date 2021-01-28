package it.jac.management.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.InvoiceBody;
import it.jac.management.repository.InvoiceBodyRepository;
import it.jac.management.service.InvoiceBodyService;

@Service
public class InvoiceBodyServiceImpl implements InvoiceBodyService {
	
	@Autowired
	InvoiceBodyRepository invoiceBodyRepository;

	@Override
	public Optional<InvoiceBody> get(Long id){
		return invoiceBodyRepository.findById(id);
	}

	@Override
	public InvoiceBody create(InvoiceBody i){
		return invoiceBodyRepository.save(i);
	}

	@Override
	public void delete(Long id){
		invoiceBodyRepository.deleteById(id);
	}

	@Override
	public InvoiceBody update(InvoiceBody invoiceBody, Long id){
		return invoiceBodyRepository.findById(id).map(c -> { // update if entity already exists
			invoiceBody.setId(c.getId());
			return create(invoiceBody);
		}).orElseGet(() -> { // create if entity not exists
			return create(invoiceBody);
		});
	}


}
