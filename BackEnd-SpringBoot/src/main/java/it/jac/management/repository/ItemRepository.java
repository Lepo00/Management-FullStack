package it.jac.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.management.model.Item;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> {
	Item findByCode(String code);
}