package it.jac.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")

@Entity
@Table(name = "tail")
public class InvoiceTail extends AuditModel{
	@Column(name = "items_value")
	@NotNull
	private Float items_value;
	
	@Column(name = "service_value")
	private Float service_value;
	
	@Column(name = "discount")
	private Float discount;
	
	@Column(name = "tail_discount")
	private Float tail_discount;

	@Column(name = "tail_discount_value")
	private Float tail_discount_value;
	
	@Column(name = "tot_discount")
	private Float tot_discount;
	
	@Column(name = "taxable")
	@NotNull
	private Float taxable;
	
	@Column(name = "tot_tax")
	private Float tot_tax;
	
	@Column(name = "net_pay")
	@NotNull
	private Float net_pay;

	public Float getItems_value() {
		return items_value;
	}

	public void setItems_value(Float items_value) {
		this.items_value = items_value;
	}

	public Float getService_value() {
		return service_value;
	}

	public void setService_value(Float service_value) {
		this.service_value = service_value;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getTail_discount() {
		return tail_discount;
	}

	public void setTail_discount(Float tail_discount) {
		this.tail_discount = tail_discount;
	}

	public Float getTail_discount_value() {
		return tail_discount_value;
	}

	public void setTail_discount_value(Float tail_discount_value) {
		this.tail_discount_value = tail_discount_value;
	}

	public Float getTot_discount() {
		return tot_discount;
	}

	public void setTot_discount(Float tot_discount) {
		this.tot_discount = tot_discount;
	}

	public Float getTaxable() {
		return taxable;
	}

	public void setTaxable(Float taxable) {
		this.taxable = taxable;
	}

	public Float getTot_tax() {
		return tot_tax;
	}

	public void setTot_tax(Float tot_tax) {
		this.tot_tax = tot_tax;
	}

	public Float getNet_pay() {
		return net_pay;
	}

	public void setNet_pay(Float net_pay) {
		this.net_pay = net_pay;
	}
	
	
}
