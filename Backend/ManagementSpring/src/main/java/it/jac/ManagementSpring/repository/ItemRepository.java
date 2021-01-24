package it.jac.ManagementSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.ManagementSpring.model.Item;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> {

}