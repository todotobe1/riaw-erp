package com.riawworks.riaw.erp.model.po;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TDeliveryNoteDetail entity. @author MyEclipse Persistence Tools
 */

public class TDeliveryNoteDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp ctime;
	private Timestamp mtime;
	private Integer deliveryNoteId;
	private Integer productId;
	private Integer unitId;
	private Integer quantity;
	private Double unitPrice;
	private Double amount;
	private Date productionDate;
	private String comments;

	// Constructors

	/** default constructor */
	public TDeliveryNoteDetail() {
	}

	/** minimal constructor */
	public TDeliveryNoteDetail(Timestamp ctime, Timestamp mtime,
			Integer deliveryNoteId, Integer productId, Integer unitId,
			Integer quantity, Double unitPrice, Double amount,
			Date productionDate) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.deliveryNoteId = deliveryNoteId;
		this.productId = productId;
		this.unitId = unitId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.productionDate = productionDate;
	}

	/** full constructor */
	public TDeliveryNoteDetail(Timestamp ctime, Timestamp mtime,
			Integer deliveryNoteId, Integer productId, Integer unitId,
			Integer quantity, Double unitPrice, Double amount,
			Date productionDate, String comments) {
		this.ctime = ctime;
		this.mtime = mtime;
		this.deliveryNoteId = deliveryNoteId;
		this.productId = productId;
		this.unitId = unitId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.productionDate = productionDate;
		this.comments = comments;
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

	public Integer getDeliveryNoteId() {
		return this.deliveryNoteId;
	}

	public void setDeliveryNoteId(Integer deliveryNoteId) {
		this.deliveryNoteId = deliveryNoteId;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getProductionDate() {
		return this.productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}