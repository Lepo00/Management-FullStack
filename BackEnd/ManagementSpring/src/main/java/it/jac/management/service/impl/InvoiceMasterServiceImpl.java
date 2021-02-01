package it.jac.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.InvoiceMaster;
import it.jac.management.repository.InvoiceBodyRepository;
import it.jac.management.repository.InvoiceMasterRepository;
import it.jac.management.service.InvoiceMasterService;

@Service
public class InvoiceMasterServiceImpl implements InvoiceMasterService {

	@Autowired
	InvoiceMasterRepository invoiceMasterRepository;

	@Autowired
	InvoiceBodyRepository invoiceBodyRepository;

	@Override
	public Optional<InvoiceMaster> get(Long id) {
		return invoiceMasterRepository.findById(id);
	}

	@Override
	public List<InvoiceMaster> getAll() {
		return invoiceMasterRepository.findAll();
	}

	@Override
	public InvoiceMaster create(InvoiceMaster i) {
		invoiceBodyRepository.saveAll(i.getRows());
		return invoiceMasterRepository.save(i);
	}

	@Override
	public void delete(Long id) {
		invoiceMasterRepository.deleteById(id);
	}

	@Override
	public InvoiceMaster update(InvoiceMaster invoiceMaster, Long id) {
		return invoiceMasterRepository.findById(id).map(c -> { // update if entity already exists
			invoiceMaster.setId(c.getId());
			return create(invoiceMaster);
		}).orElseGet(() -> { // create if entity not exists
			return create(invoiceMaster);
		});
	}

}
