package it.jac.ManagementSpring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")

@Entity
@Table(name = "item")
public class Item extends AuditModel{
	
	@Column(name = "code", unique = true)
	@NotNull
	private String code;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private int price;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
