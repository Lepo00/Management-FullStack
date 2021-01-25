package it.jac.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.management.model.Invoice;

@Repository("invoiceRepository")
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}