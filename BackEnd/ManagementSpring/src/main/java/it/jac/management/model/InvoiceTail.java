package it.jac.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")

@Entity
@Table(name = "invoice_tail")
public class InvoiceTail extends AuditModel{
	@Column(name = "items_value")
	private Float itemsValue;
	
	@Column(name = "service_value")
	private Float serviceValue;
	
	@Column(name = "discount")
	private Float discount;
	
	@Column(name = "tail_discount")
	private Float tailDiscount;

	@Column(name = "tail_discount_value")
	private Float tailDiscountValue;
	
	@Column(name = "tot_discount")
	private Float totDiscount;
	
	@Column(name = "taxable")
	private Float taxable;
	
	@Column(name = "tot_tax")
	private Float totTax;
	
	@Column(name = "net_pay")
	private Float netPay;

	public Float getItemsValue() {
		return itemsValue;
	}

	public void setItemsValue(Float itemsValue) {
		this.itemsValue = itemsValue;
	}

	public Float getServiceValue() {
		return serviceValue;
	}

	public void setServiceValue(Float serviceValue) {
		this.serviceValue = serviceValue;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getTailDiscount() {
		return tailDiscount;
	}

	public void setTailDiscount(Float tailDiscount) {
		this.tailDiscount = tailDiscount;
	}

	public Float getTailDiscountValue() {
		return tailDiscountValue;
	}

	public void setTailDiscountValue(Float tailDiscountValue) {
		this.tailDiscountValue = tailDiscountValue;
	}

	public Float getTotDiscount() {
		return totDiscount;
	}

	public void setTotDiscount(Float totDiscount) {
		this.totDiscount = totDiscount;
	}

	public Float getTaxable() {
		return taxable;
	}

	public void setTaxable(Float taxable) {
		this.taxable = taxable;
	}

	public Float getTotTax() {
		return totTax;
	}

	public void setTotTax(Float totTax) {
		this.totTax = totTax;
	}

	public Float getNetPay() {
		return netPay;
	}

	public void setNetPay(Float netPay) {
		this.netPay = netPay;
	}
}
