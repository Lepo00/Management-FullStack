package it.jac.ManagementSpring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

}
