package it.jac.management.service;

import java.util.Optional;

import it.jac.management.model.Customer;

public interface CustomerService {

	public Optional<Customer> get(Long id);

	public Customer create(Customer c);
	
	public void delete(Long id);
	
	public Customer update(Customer c, Long id);

}