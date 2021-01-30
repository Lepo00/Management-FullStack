package it.jac.management.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")

@Entity
@Table(name = "customer")
public class Customer extends AuditModel {

	@Column(name = "fiscal_code", unique = true)
	@NotNull
	private String fiscalCode;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "surname")
	@NotNull
	private String surname;

	@Column(name = "address")
	@NotNull
	private String address;

	@Column(name = "phone")
	@NotNull
	private String phone;

	@OneToMany
	@JoinColumn(name = "customer_id")
	private List<InvoiceMaster> invoices;

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<InvoiceMaster> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceMaster> invoices) {
		this.invoices = invoices;
	}

}
