package it.jac.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")

@Entity
@Table(name = "invoice_tail")
public class InvoiceTail extends AuditModel {	
	@Column(name = "items_value")
	private double itemsValue;

	@Column(name = "service_value")
	private double serviceValue;

	@Column(name = "rows_discount")
	private double rowsDiscount;

	@Column(name = "discount_perc")
	private double discountPerc;

	@Column(name = "discount_value")
	private double discountValue;	

	@Column(name = "tot_discount")
	private double totDiscount;

	@Column(name = "taxable")
	private double taxable;

	@Column(name = "taxed")
	private double taxed;

	@Column(name = "final_amount")
	private double finalAmount;
	
	public double getRowsDiscount() {
		return rowsDiscount;
	}

	public void setRowsDiscount(double rowsDiscount) {
		this.rowsDiscount = rowsDiscount;
	}
	public double getItemsValue() {
		return itemsValue;
	}

	public void setItemsValue(double itemsValue) {
		this.itemsValue = itemsValue;
	}

	public double getServiceValue() {
		return serviceValue;
	}

	public void setServiceValue(double serviceValue) {
		this.serviceValue = serviceValue;
	}
	
	public double getDiscountPerc() {
		return discountPerc;
	}

	public void setDiscountPerc(double discountPerc) {
		this.discountPerc = discountPerc;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public double getTotDiscount() {
		return totDiscount;
	}

	public void setTotDiscount(double totDiscount) {
		this.totDiscount = totDiscount;
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
