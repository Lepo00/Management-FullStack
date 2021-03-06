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
import org.springframework.web.bind.annotation.RestController;

import it.jac.management.model.Item;
import it.jac.management.model.ResponseMessage;
import it.jac.management.model.UnitOfMeasure;
import it.jac.management.service.ItemService;
import it.jac.management.service.UnitOfMeasureService;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@Autowired
	UnitOfMeasureService unitService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Item> items = itemService.getAll();
		if (items != null) {
			return ResponseEntity.ok(items);
		} else {
			return ResponseEntity.badRequest().body(new ResponseMessage("No items found!"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<Item> i = itemService.get(id);
		if (i.isPresent()) {
			return ResponseEntity.ok(i.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Item doesn't exists"));
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> newItem(@RequestBody Item item) throws Exception {
		try {
			Item save = itemService.create(item);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Item Not Saved!"));
		}
	}

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody Item item) {
		try {
			Item update = itemService.update(item, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Item Not Updated!"));
		}
	}

	@DeleteMapping(path = "delete/{id}")
	public ResponseEntity<ResponseMessage> deleteItem(@PathVariable Long id) {
		try {
			itemService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("Item deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Item doesn't exists"));
		}
	}
	
	@PostMapping(path = "/{id}/addItem")
	public ResponseEntity<?> addCustomer(@PathVariable Long id, @RequestBody Item item) throws Exception {
		try {
			UnitOfMeasure unit = unitService.get(id).get();
			Item exists = itemService.getByCode(item.getCode());
			if (exists != null) {
				item.setUnitOfMeasure(unit);
			} else {
				item.setUnitOfMeasure(unit);
				itemService.create(item);
			}
			itemService.update(item, item.getId());
			return ResponseEntity.ok(item);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Item not found"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Item Not Added!"));
		}
	}

}
