package it.jac.management.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import it.jac.management.model.ResponseMessage;
import it.jac.management.model.User;
import it.jac.management.service.CustomerService;
import it.jac.management.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	CustomerService customerService;

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<User> c = userService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found"));
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> newUser(@RequestBody User user) throws Exception {
		try {
			User save = userService.create(user);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("User Not Saved!"));
		}
	}

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
		try {
			User update = userService.update(user, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("User Not Updated!"));
		}
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long id) {
		try {
			userService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("User deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found"));
		}
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) throws Exception {
		try {
			User login = userService.login(username, password);
			if (login == null)
				throw new Exception();
			return ResponseEntity.ok(login);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("User Not Found!"));
		}
	}

	@PostMapping(path = "/{id}/addCustomer")
	public ResponseEntity<?> addCustomer(@PathVariable Long id, @RequestBody Customer customer) throws Exception {
		try {
			User user = userService.get(id).get();
			Customer exists = customerService.getByIvaCode(customer.getIvaCode());
			if (exists != null) {
				user.getCustomers().add(exists);
			} else {
				user.getCustomers().add(customer);
				customerService.create(customer);
			}
			userService.update(user, user.getId());
			return ResponseEntity.ok(user);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Customer Not Added!"));
		}
	}

	@PostMapping(path = "/{id}/addCustomers")
	public ResponseEntity<?> addCustomers(@PathVariable Long id, @RequestBody List<Customer> customers)
			throws Exception {
		try {
			User user = userService.get(id).get();
			user.getCustomers().addAll(customers);
			customerService.createAll(customers);
			userService.create(user);
			return ResponseEntity.ok(user);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Customers Not Added!"));
		}
	}
	
	@GetMapping(path = "/{id}/invoices")
	public ResponseEntity<?> getInvoices(@PathVariable Long id) throws Exception {
		try {
			User user = userService.get(id).get();
			return ResponseEntity.ok(user.getInvoices());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found"));
		}
	}
}
