package it.jac.management.service;

import java.util.Optional;

import it.jac.management.model.Item;

public interface ItemService {

	public Optional<Item> get(Long id);

	public Item create(Item i);
	
	public void delete(Long id);
	
	public Item update(Item i, Long id);

}