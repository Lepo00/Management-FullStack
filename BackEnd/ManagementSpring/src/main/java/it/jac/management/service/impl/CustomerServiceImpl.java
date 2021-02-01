package it.jac.management.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.Customer;
import it.jac.management.repository.CustomerRepository;
import it.jac.management.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Optional<Customer> get(Long id){
		return customerRepository.findById(id);
	}

	@Override
	public Customer create(Customer c){
		if(customerRepository.findByFiscalCode(c.getFiscalCode())==null)
			return customerRepository.save(c);
		return null;
	}

	@Override
	public void delete(Long id){
		customerRepository.deleteById(id);
	}

	@Override
	public Customer update(Customer customer, Long id){
		return customerRepository.findById(id).map(c -> { // update if entity already exists
			customer.setId(c.getId());
			return create(customer);
		}).orElseGet(() -> { // create if entity not exists
			return create(customer);
		});
	}
	
	@Override
	public Customer login(String u, String p) {
		return customerRepository.findByUsernameAndPassword(u, p);
	}


}
