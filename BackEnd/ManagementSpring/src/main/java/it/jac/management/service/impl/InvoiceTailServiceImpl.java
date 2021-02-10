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
		double tot = 0;
		double rowdisc = 0;
		for (InvoiceBody row : i.getRows()) {
			tot += row.getTaxable();
			rowdisc += row.getTotDiscount();
		}
		tail.setItemsValue(tot);
		tail.setRowsDiscount(rowdisc);
		tail.setDiscountValue(tot * tail.getDiscountPerc() / 100);
		tail.setTotDiscount(tail.getDiscountValue() + rowdisc);
		tail.setTaxable(tot - tail.getDiscountValue());
		tail.setTaxed(tail.getTaxable() * 22 / 100);
		tail.setFinalAmount(tail.getTaxable() + tail.getTaxed());
		return tail;
	}

}
