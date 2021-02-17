package it.jac.management.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")

@Entity
@Table(name = "invoice_master")
public class InvoiceMaster extends AuditModel {
	@Column(name = "accountholder")
	@NotNull
	private String accountholder;

	@Column(name = "date")
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date date;

	@Column(name = "number")
	private int number;
	
	@Column(name = "payment_method")
	private String paymentMethod;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_master_id")
	private List<InvoiceBody> rows;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_tail_id")
	private InvoiceTail tail;

	public String getAccountholder() {
		return accountholder;
	}

	public void setAccountholder(String accountholder) {
		this.accountholder = accountholder;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<InvoiceBody> getRows() {
		return rows;
	}

	public void setRows(List<InvoiceBody> rows) {
		this.rows = rows;
	}

	public InvoiceTail getTail() {
		return tail;
	}

	public void setTail(InvoiceTail tail) {
		this.tail = tail;
	}
}
