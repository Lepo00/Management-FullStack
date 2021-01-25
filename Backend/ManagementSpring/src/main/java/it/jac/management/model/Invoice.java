package it.jac.management.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")

@Entity
@Table(name = "invoice")
public class Invoice extends AuditModel{
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "intestatario")
	@NotNull
	private String intestatario;
	
	@Column(name = "date")
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date date;
	
	@Column(name = "shipment_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date shipmentDate;
	
	@Column(name = "net_price")
	private int netPrice;
	
	@Column(name = "taxes")
	private int taxes;
	
	@Column(name = "tot_price")
	private int totPrice;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@ManyToMany
	@JoinTable(name="invoice_item", joinColumns = @JoinColumn(name="item_id"),
	inverseJoinColumns=@JoinColumn(name="invoice_id"))
	private List<Item> items;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public int getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(int netPrice) {
		this.netPrice = netPrice;
	}

	public int getTaxes() {
		return taxes;
	}

	public void setTaxes(int taxes) {
		this.taxes = taxes;
	}

	public int getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
