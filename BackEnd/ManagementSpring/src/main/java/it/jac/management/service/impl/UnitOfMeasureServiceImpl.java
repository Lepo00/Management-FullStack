package it.jac.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.UnitOfMeasure;
import it.jac.management.repository.UnitOfMeasureRepository;
import it.jac.management.service.UnitOfMeasureService;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Override
	public List<UnitOfMeasure> getAll() {
		return unitOfMeasureRepository.findAll();
	}

}
