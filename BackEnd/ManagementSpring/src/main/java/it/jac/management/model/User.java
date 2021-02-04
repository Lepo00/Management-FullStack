package it.jac.management.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")

@Entity
@Table(name = "user")
public class User extends AuditModel {

	@Column(name = "iva_code", unique = true)
	@NotNull
	private String ivaCode;

	@Column(name = "address")
	@NotNull
	private String address;

	@Column(name = "phone")
	@NotNull
	private String phone;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@OneToMany
	@JoinColumn(name = "user_id")
	private List<InvoiceMaster> invoices;
	
	
	@ManyToMany
	@JoinTable(name = "user_customer", 
	joinColumns = @JoinColumn(name = "customer_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<Customer> customers;

	public String getIvaCode() {
		return ivaCode;
	}

	public void setIvaCode(String ivaCode) {
		this.ivaCode = ivaCode;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
