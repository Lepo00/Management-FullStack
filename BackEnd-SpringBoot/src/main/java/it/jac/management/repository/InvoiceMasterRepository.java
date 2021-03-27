package it.jac.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.management.model.InvoiceMaster;

@Repository("invoiceMasterRepository")
public interface InvoiceMasterRepository extends JpaRepository<InvoiceMaster, Long> {

}