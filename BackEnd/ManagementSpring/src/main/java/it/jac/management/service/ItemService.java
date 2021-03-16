package it.jac.management.service;

import java.util.List;
import java.util.Optional;

import it.jac.management.model.Item;

public interface ItemService {

	public Optional<Item> get(Long id);
	
	public List<Item> getAll();

	public Item create(Item i);

	public void delete(Long id);

	public Item update(Item i, Long id);
	
	public Item getByCode(String code);

}