package com.riawworks.riaw.erp.model.bo;

import java.util.Date;
import java.util.List;

public class DeliveryNote {
	private Integer id;
	private String recordNo;
	private String customer;
	private String deliveryVan;
	private Date deliveryDate;
	private String orderNo;
	private String deliveryMan;
	private Integer containerDeliver;
	private Integer containerTake;
	private List<DeliveryNoteDetail> deliveryNoteDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDeliveryVan() {
		return deliveryVan;
	}

	public void setDeliveryVan(String deliveryVan) {
		this.deliveryVan = deliveryVan;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDeliveryMan() {
		return deliveryMan;
	}

	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}

	public Integer getContainerDeliver() {
		return containerDeliver;
	}

	public void setContainerDeliver(Integer containerDeliver) {
		this.containerDeliver = containerDeliver;
	}

	public Integer getContainerTake() {
		return containerTake;
	}

	public void setContainerTake(Integer containerTake) {
		this.containerTake = containerTake;
	}

	public List<DeliveryNoteDetail> getDeliveryNoteDetails() {
		return deliveryNoteDetails;
	}

	public void setDeliveryNoteDetails(
			List<DeliveryNoteDetail> deliveryNoteDetails) {
		this.deliveryNoteDetails = deliveryNoteDetails;
	}

}
