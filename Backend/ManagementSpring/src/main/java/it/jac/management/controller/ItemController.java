package it.jac.management.controller;

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
import org.springframework.web.bind.annotation.RestController;

import it.jac.management.model.Item;
import it.jac.management.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id){
		Optional<Item> i = itemService.get(id);
		if (i.isPresent()) {
			return ResponseEntity.ok(i.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item doesn't exists");
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> newItem(@RequestBody Item item) throws Exception {
		try {
			Item save = itemService.create(item);
			if(save==null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Item Not Saved!");
		}
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> updateItem(@PathVariable Long id,
			@RequestBody Item item) {
		try {
			Item update = itemService.update(item, id);
			return ResponseEntity.ok(update);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("Item Not Updated!");
		}
	}
	
	@DeleteMapping(path="delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
		try {
			itemService.delete(id);
			return ResponseEntity.ok().body("Item deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item doesn't exists");
		}
	}
	
}
