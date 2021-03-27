package it.jac.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")

@Entity
@Table(name = "invoice_body")
public class InvoiceBody extends AuditModel {
	@ManyToOne
	private Item item;

	@Column
	private int quantity;

	@Column(name = "perc_discount")
	private int percDiscount;

	@Column(name = "discount_value")
	private double totDiscount;

	@Column(name = "net_price")
	private double netPrice;

	@Column(name = "taxable")
	private double taxable;

	@Column(name = "taxed")
	private double taxed;

	@Column(name = "final_amount")
	private double finalAmount;

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

	public double getTotDiscount() {
		return totDiscount;
	}

	public void setTotDiscount(double totDiscount) {
		this.totDiscount = totDiscount;
	}

	public double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}

	public double getTaxable() {
		return taxable;
	}

	public void setTaxable(double taxable) {
		this.taxable = taxable;
	}

	public double getTaxed() {
		return taxed;
	}

	public void setTaxed(double taxed) {
		this.taxed = taxed;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}
}
