package it.jac.management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.management.model.Customer;
import it.jac.management.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Customer> get(@PathVariable Long id){
		Optional<Customer> c = customerService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> newCustomer(@RequestBody Customer customer) throws Exception {
		try {
			Customer save = customerService.create(customer);
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Customer Not Saved!");
		}
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Long id,
			@RequestBody Customer customer) {
		try {
			Customer save = customerService.update(customer, id);
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Customer Not Updated!");
		}
	}
	
	@DeleteMapping(path="delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
		try {
			customerService.delete(id);
			return ResponseEntity.ok().body("Customer deleted");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
