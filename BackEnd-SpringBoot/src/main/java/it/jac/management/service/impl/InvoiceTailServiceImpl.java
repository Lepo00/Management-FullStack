package it.jac.management.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.InvoiceBody;
import it.jac.management.model.InvoiceMaster;
import it.jac.management.model.InvoiceTail;
import it.jac.management.repository.InvoiceTailRepository;
import it.jac.management.service.InvoiceTailService;
import it.jac.management.service.ItemService;

@Service
public class InvoiceTailServiceImpl implements InvoiceTailService {

	@Autowired
	InvoiceTailRepository invoiceTailRepository;

	@Autowired
	ItemService itemService;

	@Override
	public Optional<InvoiceTail> get(Long id) {
		return invoiceTailRepository.findById(id);
	}

	@Override
	public InvoiceTail create(InvoiceTail i) {
		return invoiceTailRepository.save(i);
	}

	@Override
	public void delete(Long id) {
		invoiceTailRepository.deleteById(id);
	}

	@Override
	public InvoiceTail update(InvoiceTail invoiceTail, Long id) {
		return invoiceTailRepository.findById(id).map(c -> { // update if entity already exists
			invoiceTail.setId(c.getId());
			return create(invoiceTail);
		}).orElseGet(() -> { // create if entity not exists
			return create(invoiceTail);
		});
	}

	@Override
	public InvoiceTail calc(InvoiceMaster i) {
		InvoiceTail tail = i.getTail();
		double tot = 0, rowdisc = 0;
		for (InvoiceBody row : i.getRows()) {
			tot += row.getTaxable();
			rowdisc += row.getTotDiscount();
		}
		tail.setItemsValue(tot);
		tail.setRowsDiscount(rowdisc);
		tail.setDiscountValue(tot * tail.getPercDiscount() / 100);
		tail.setTotDiscount(tail.getDiscountValue() + rowdisc);
		tail.setTaxable(tot - tail.getDiscountValue());
		tail.setTaxed(tail.getTaxable() * 22 / 100);
		tail.setFinalAmount(tail.getTaxable() + tail.getTaxed());
		roundUp(tail);
		return tail;
	}
	
	public void roundUp(InvoiceTail tail) {
		//tail.setItemsValue(Math.round(tail.getItemsValue() * 100.0) / 100.0);
		//tail.setRowsDiscount(Math.round(tail.getRowsDiscount() * 100.0) / 100.0);
		tail.setDiscountValue(Math.round(tail.getDiscountValue() * 100.0) / 100.0);
		tail.setTotDiscount(Math.round(tail.getTotDiscount() * 100.0) / 100.0);
		tail.setTaxable(Math.round(tail.getTaxable() * 100.0) / 100.0);
		tail.setTaxed(Math.round(tail.getTaxed() * 100.0) / 100.0);
		tail.setFinalAmount(Math.round(tail.getFinalAmount() * 100.0) / 100.0);
	}

}
