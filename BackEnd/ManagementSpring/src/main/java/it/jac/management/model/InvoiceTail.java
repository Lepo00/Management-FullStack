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

	@Column(name = "discount")
	private double discount;

	@Column(name = "tail_discount")
	private double tailDiscount;

	@Column(name = "tail_discount_value")
	private double tailDiscountValue;

	@Column(name = "tot_discount")
	private double totDiscount;

	@Column(name = "taxable")
	private double taxable;

	@Column(name = "tot_tax")
	private double totTax;

	@Column(name = "net_pay")
	private double netPay;

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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTailDiscount() {
		return tailDiscount;
	}

	public void setTailDiscount(double tailDiscount) {
		this.tailDiscount = tailDiscount;
	}

	public double getTailDiscountValue() {
		return tailDiscountValue;
	}

	public void setTailDiscountValue(double tailDiscountValue) {
		this.tailDiscountValue = tailDiscountValue;
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

	public double getTotTax() {
		return totTax;
	}

	public void setTotTax(double totTax) {
		this.totTax = totTax;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
}
