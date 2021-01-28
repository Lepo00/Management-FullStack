package it.jac.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.management.model.InvoiceTail;

@Repository("InvoiceTailRepository")
public interface InvoiceTailRepository extends JpaRepository<InvoiceTail, Long> {

}
