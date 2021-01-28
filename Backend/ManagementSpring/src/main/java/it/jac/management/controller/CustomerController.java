package it.jac.management.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.jac.management.model.Customer;
import it.jac.management.model.Invoice;
import it.jac.management.model.Item;
import it.jac.management.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id){
		Optional<Customer> c = customerService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer doesn't exists");
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> newCustomer(@RequestBody Customer customer) throws Exception {
		try {
			Customer save = customerService.create(customer);
			if(save==null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Customer Not Saved!");
		}
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Long id,
			@RequestBody Customer customer) {
		try {
			Customer update = customerService.update(customer, id);
			return ResponseEntity.ok(update);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("Customer Not Updated!");
		}
	}
	
	@DeleteMapping(path="delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
		try {
			customerService.delete(id);
			return ResponseEntity.ok().body("Customer deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer doesn't exists");
		}
	}
	
	@PostMapping(path = "order/{id}")
	public ResponseEntity<String> order(@PathVariable Long id, @RequestParam List<Item> items, @RequestParam String description){
		Optional<Customer> c = customerService.get(id);
		if(c.isPresent() && !items.isEmpty()) {
			Customer cust= c.get();
			Invoice inv= new Invoice();
			inv.setDescription(description);
			inv.setAccountholder(cust.getName()+" "+cust.getSurname());
			inv.setDate(new Date());
			inv.setShipmentDate(new Date(new Date().getTime() + 7*24*60*60*1000));
			inv.setTotPrice(calcPrice(items));
			inv.setNetPrice(inv.getTotPrice()/1.22);
			inv.setTaxes(inv.getTotPrice()/1.78);
			inv.setItems(items);
			cust.getInvoices().add(inv);
		}
		return null;
	}
	
	public int calcPrice(List<Item> items) {
		int price=0;
		for(Item i: items) {
			price+=i.getPrice()*i.getQuantity();
		}
		return price;
	}
}
