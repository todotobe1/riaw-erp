package com.riawworks.riaw.erp.framework.model.vo;

public class CodeVo {

	private String value;
	private String name;

	public CodeVo(String value, String name) {
		super();
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
