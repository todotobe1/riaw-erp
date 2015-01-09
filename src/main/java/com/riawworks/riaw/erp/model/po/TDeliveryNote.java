package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TDeliveryNote entity. @author MyEclipse Persistence Tools
 */

public class TDeliveryNote implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private String recordNo;
	private String customer;
	private String deliveryVan;
	private Date deliveryDate;
	private String orderNo;
	private String deliveryMan;
	private Integer containerDeliver;
	private Integer containerTake;
	private String enabled;

	// Constructors

	/** default constructor */
	public TDeliveryNote() {
	}

	/** minimal constructor */
	public TDeliveryNote(Timestamp ctime, Timestamp mtime, String recordNo,
			String customer, String deliveryVan, Date deliveryDate,
			String orderNo, String deliveryMan, String enabled) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.recordNo = recordNo;
		this.customer = customer;
		this.deliveryVan = deliveryVan;
		this.deliveryDate = deliveryDate;
		this.orderNo = orderNo;
		this.deliveryMan = deliveryMan;
		this.enabled = enabled;
	}

	/** full constructor */
	public TDeliveryNote(Timestamp ctime, Timestamp mtime, String recordNo,
			String customer, String deliveryVan, Date deliveryDate,
			String orderNo, String deliveryMan, Integer containerDeliver,
			Integer containerTake, String enabled) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.recordNo = recordNo;
		this.customer = customer;
		this.deliveryVan = deliveryVan;
		this.deliveryDate = deliveryDate;
		this.orderNo = orderNo;
		this.deliveryMan = deliveryMan;
		this.containerDeliver = containerDeliver;
		this.containerTake = containerTake;
		this.enabled = enabled;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getMtime() {
		return this.mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	public String getRecordNo() {
		return this.recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDeliveryVan() {
		return this.deliveryVan;
	}

	public void setDeliveryVan(String deliveryVan) {
		this.deliveryVan = deliveryVan;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDeliveryMan() {
		return this.deliveryMan;
	}

	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}

	public Integer getContainerDeliver() {
		return this.containerDeliver;
	}

	public void setContainerDeliver(Integer containerDeliver) {
		this.containerDeliver = containerDeliver;
	}

	public Integer getContainerTake() {
		return this.containerTake;
	}

	public void setContainerTake(Integer containerTake) {
		this.containerTake = containerTake;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}