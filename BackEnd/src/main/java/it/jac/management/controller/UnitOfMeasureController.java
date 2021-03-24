package it.jac.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.management.model.ResponseMessage;
import it.jac.management.model.UnitOfMeasure;
import it.jac.management.service.UnitOfMeasureService;

@RestController
@RequestMapping("/unit")
@CrossOrigin(origins = "http://localhost:4200")
public class UnitOfMeasureController {
	
	@Autowired
	UnitOfMeasureService unitOfMeasureService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<UnitOfMeasure> measures = unitOfMeasureService.getAll();
		if (!measures.isEmpty()) {
			return ResponseEntity.ok(measures);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Measures empty!"));
		}
	}
}
