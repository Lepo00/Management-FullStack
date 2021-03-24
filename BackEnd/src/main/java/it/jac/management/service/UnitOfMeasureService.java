package it.jac.management.service;

import java.util.List;
import java.util.Optional;

import it.jac.management.model.UnitOfMeasure;

public interface UnitOfMeasureService {

	public List<UnitOfMeasure> getAll();
	
	public Optional<UnitOfMeasure> get(Long id);
}