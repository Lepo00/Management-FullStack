package it.jac.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.Customer;
import it.jac.management.model.InvoiceMaster;
import it.jac.management.repository.CustomerRepository;
import it.jac.management.repository.InvoiceBodyRepository;
import it.jac.management.repository.InvoiceMasterRepository;
import it.jac.management.repository.InvoiceTailRepository;
import it.jac.management.service.InvoiceMasterService;
import it.jac.management.service.InvoiceTailService;

@Service
public class InvoiceMasterServiceImpl implements InvoiceMasterService {

	@Autowired
	InvoiceMasterRepository invoiceMasterRepository;

	@Autowired
	InvoiceBodyRepository invoiceBodyRepository;
	
	@Autowired
	InvoiceTailRepository InvoiceTailRepository;
	
	@Autowired
	InvoiceTailService tailService;

	@Autowired
	CustomerRepository customerRepository;

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
		i.setTail(tailService.calc(i));
		InvoiceTailRepository.save(i.getTail());
		return invoiceMasterRepository.save(i);
	}

	@Override
	public InvoiceMaster createWithCustomer(Long idCustomer, InvoiceMaster invoice) {
		Customer customer = customerRepository.getOne(idCustomer);
		invoice.setAccountholder(customer.getName() + " " + customer.getSurname());
		return create(invoice);
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
