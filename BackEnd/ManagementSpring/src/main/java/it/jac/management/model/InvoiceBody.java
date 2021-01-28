package it.jac.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")

@Entity
@Table(name = "invoice_body")
public class InvoiceBody extends AuditModel{	
	@ManyToOne
	private Item item;
	
	@Column
	private int quantity;
	
	@Column(name = "perc_discount")
	private int percDiscount;
	
	@Column(name = "total_discount")
	private int totDiscount;
	
	@Column(name = "net_price")
	private double netPrice;
	
}
