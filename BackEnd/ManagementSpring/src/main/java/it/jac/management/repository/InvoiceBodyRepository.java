package it.jac.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.management.model.InvoiceBody;

@Repository("InvoiceBodyRepository")
public interface InvoiceBodyRepository extends JpaRepository<InvoiceBody, Long> {

}
