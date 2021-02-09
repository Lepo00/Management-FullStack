package it.jac.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.InvoiceBody;
import it.jac.management.model.InvoiceMaster;
import it.jac.management.model.InvoiceTail;
import it.jac.management.repository.InvoiceTailRepository;
import it.jac.management.service.InvoiceTailService;

@Service
public class InvoiceTailServiceImpl implements InvoiceTailService {

	@Autowired
	InvoiceTailRepository invoiceTailRepository;

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
		float tot = 0;
		float totDisc;
		List<InvoiceBody> body = i.getRows();
		for (InvoiceBody item : body) {
			tot += ((item.getQuantity()*item.getItem().getPrice())*(item.getPercDiscount()/100));
		}
		i.getTail().setItemsValue(tot);
		i.getTail().setDiscount(10.0f);
		i.getTail().setTaxable(tot*i.getTail().getDiscount()/100);
		i.getTail().setTotTax(i.getTail().getTaxable()*22/100);
		i.getTail().setNetPay(i.getTail().getTaxable()+i.getTail().getTotTax());
		return i.getTail();
	}

}
