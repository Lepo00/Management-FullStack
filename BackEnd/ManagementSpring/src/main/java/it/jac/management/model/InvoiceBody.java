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
	
//	@OneToOne
//	private InvoiceTail tail;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPercDiscount() {
		return percDiscount;
	}

	public void setPercDiscount(int percDiscount) {
		this.percDiscount = percDiscount;
	}

	public int getTotDiscount() {
		return totDiscount;
	}

	public void setTotDiscount(int totDiscount) {
		this.totDiscount = totDiscount;
	}

	public double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}

//	public InvoiceTail getTail() {
//		return tail;
//	}
//
//	public void setTail(InvoiceTail tail) {
//		this.tail = tail;
//	}
	
}
