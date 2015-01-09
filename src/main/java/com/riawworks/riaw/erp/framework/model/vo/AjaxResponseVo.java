package com.riawworks.riaw.erp.framework.model.vo;

public class AjaxResponseVo<T> {

	public static final String SUCCEED = "1";

	public static final String FAIL = "0";

	private String status;
	private String msg;
	private T data;

	public AjaxResponseVo() {

	}

	public AjaxResponseVo(String status) {
		this.status = status;
	}

	public AjaxResponseVo(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public AjaxResponseVo(String status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
