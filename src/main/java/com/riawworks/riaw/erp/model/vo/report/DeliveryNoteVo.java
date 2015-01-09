package com.riawworks.riaw.erp.model.vo.report;

import java.util.Date;
import java.util.List;

public class DeliveryNoteVo {

	private String recordNo;
	private String customer;
	private String deliveryVan;
	private Date deliveryDate;
	private String orderNo;
	private String deliveryMan;
	private Integer containerDeliver;
	private Integer containerTake;
	private List<DeliveryNoteDetailVo> deliveryNoteDetailVos;

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

	public List<DeliveryNoteDetailVo> getDeliveryNoteDetailVos() {
		return deliveryNoteDetailVos;
	}

	public void setDeliveryNoteDetailVos(
			List<DeliveryNoteDetailVo> deliveryNoteDetailVos) {
		this.deliveryNoteDetailVos = deliveryNoteDetailVos;
	}

}
