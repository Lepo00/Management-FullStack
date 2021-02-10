package it.jac.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.InvoiceBody;
import it.jac.management.model.Item;
import it.jac.management.repository.InvoiceBodyRepository;
import it.jac.management.service.InvoiceBodyService;
import it.jac.management.service.ItemService;

@Service
public class InvoiceBodyServiceImpl implements InvoiceBodyService {

	@Autowired
	InvoiceBodyRepository invoiceBodyRepository;

	@Autowired
	ItemService itemService;

	@Override
	public Optional<InvoiceBody> get(Long id) {
		return invoiceBodyRepository.findById(id);
	}

	@Override
	public InvoiceBody create(InvoiceBody i) {
		return invoiceBodyRepository.save(i);
	}

	@Override
	public List<InvoiceBody> createAll(List<InvoiceBody> invoices) {
		return invoiceBodyRepository.saveAll(invoices);
	}

	@Override
	public void delete(Long id) {
		invoiceBodyRepository.deleteById(id);
	}

	@Override
	public InvoiceBody update(InvoiceBody invoiceBody, Long id) {
		return invoiceBodyRepository.findById(id).map(c -> { // update if entity already exists
			invoiceBody.setId(c.getId());
			return create(invoiceBody);
		}).orElseGet(() -> { // create if entity not exists
			return create(invoiceBody);
		});
	}

	@Override
	public List<InvoiceBody> calc(List<InvoiceBody> rows) {
		Item item;
		for (InvoiceBody row : rows) {
			item = itemService.getOne(row.getItem().getId());
			double discount = (item.getPrice() * row.getPercDiscount() / 100);

			row.setTotDiscount(item.getPrice() * row.getQuantity() * row.getPercDiscount() / 100);
			row.setNetPrice(item.getPrice() - discount);
			row.setTaxable(row.getNetPrice() * row.getQuantity());
			row.setTaxed(row.getTaxable() * 22 / 100);
			row.setFinalAmount(row.getTaxable() + row.getTaxed());
		}
		return rows;
	}

}
