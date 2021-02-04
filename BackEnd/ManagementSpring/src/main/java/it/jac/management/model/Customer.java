package it.jac.management.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")

@Entity
@Table(name = "customer")
public class Customer extends AuditModel{
	
	@Column(name = "iva_code")
	@NotNull
	private String ivaCode;
		
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	public String getIvaCode() {
		return ivaCode;
	}

	public void setIvaCode(String ivaCode) {
		this.ivaCode = ivaCode;
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

	@Column(name = "address")
	@NotNull
	private String address;
	
	@Column(name = "phone")
	@NotNull
	private String phone;
	

	
	
}
