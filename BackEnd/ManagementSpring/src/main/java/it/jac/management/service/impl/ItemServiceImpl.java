package it.jac.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.Item;
import it.jac.management.repository.ItemRepository;
import it.jac.management.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepository;

	@Override
	public Optional<Item> get(Long id) {
		return itemRepository.findById(id);
	}

	@Override
	public List<Item> getAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item create(Item c) {
		return itemRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		itemRepository.deleteById(id);
	}

	@Override
	public Item update(Item item, Long id) {
		return itemRepository.findById(id).map(c -> { // update if entity already exists
			item.setId(c.getId());
			return create(item);
		}).orElseGet(() -> { // create if entity not exists
			return create(item);
		});
	}

}
